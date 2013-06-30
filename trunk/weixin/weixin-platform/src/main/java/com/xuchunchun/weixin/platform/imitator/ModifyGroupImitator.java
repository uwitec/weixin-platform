package com.xuchunchun.weixin.platform.imitator;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.FriendGroupBean.DefaultGroup;
import com.xuchunchun.weixin.platform.bean.FriendGroupBean.GroupFunc;
import com.xuchunchun.weixin.platform.bean.FriendGroupBean;
import com.xuchunchun.weixin.platform.bean.ModifyGroupRspBean;
import com.xuchunchun.weixin.platform.bean.PlatformMsgRspBean;

public class ModifyGroupImitator extends WebSiteImitator {
	private Logger logger = Logger.getLogger(ModifyGroupImitator.class);

	private FriendGroupBean friendGroupBean = null;
	
	private ModifyGroupRspBean modifyGroupRspBean = null;
		
	private boolean result = false;
	
	public ModifyGroupImitator(FriendGroupBean friendGroupBean){
		this.friendGroupBean = friendGroupBean;
	}
	
	public ModifyGroupImitator(GroupFunc func,String name,int groupId){
		friendGroupBean = new FriendGroupBean();
		friendGroupBean.setFunc(func);
		friendGroupBean.setName(name);
		friendGroupBean.setId(groupId);
    }
	
	public ModifyGroupImitator(GroupFunc func,String name){
		friendGroupBean = new FriendGroupBean();
		friendGroupBean.setFunc(func);
		friendGroupBean.setName(name);
    }
	
	private boolean isDefaultGroup(int groupId){
		for(DefaultGroup group : DefaultGroup.values()){
			if(groupId == group.getDefaultGroupId()){
				return true;
			}
		}
		return false;
	}

	@Override
	protected String getRspText() throws Exception {
		logger.info("send the modify group message...");
    	
    	friendGroupBean.setToken(token);
    	
    	if(isDefaultGroup(friendGroupBean.getId())){
    		throw new BusinessLogicException("0017", "");
    	}
    	
		
		try {
        	
        	String url = platformParaFactory.getParameter(PlatformConstants.MODIFY_GROUP);
        	String refererUrl = platformParaFactory.getParameter(PlatformConstants.INDEX_URL);
        	
        	logger.info("send the modify group message, the url is "+url);
            
        	PostMethod post = new PostMethod(url);
            post.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
            post.setRequestHeader(PlatformConstants.REFERER_H, refererUrl);
            post.setRequestHeader("Cookie", this.cookieStr);
            Part[] parts = null;
            parts = new Part[]{
                    new StringPart("token", friendGroupBean.getToken()),
                    new StringPart("func", friendGroupBean.getFunc()),
                    new StringPart("name", friendGroupBean.getName(),PlatformConstants.UTF_8),
                    new StringPart("id", String.valueOf(friendGroupBean.getId())),
                    new StringPart("t", "ajax-friend-group")};
            
            RequestEntity entity = new MultipartRequestEntity(parts,post.getParams());
            post.setRequestEntity(entity);
            int status = client.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                
                logger.info("the reponse message is "+text);
                
                return text;
            }
            return null;
        }catch (Exception e) {
            logger.info("error of modify the group..");
            return null;
        }
	}

	@Override
	protected void processRspText(String rspText) throws Exception {
		modifyGroupRspBean = JsonUtil.parseToBean(rspText, ModifyGroupRspBean.class);
    	
    	if("".equals(modifyGroupRspBean.getErrCode())){
    		result = true;
    	}else{
    		result = false;
    	}
		
	}

	

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	protected boolean doCheck() {
		if(friendGroupBean == null)return false;
		String func = friendGroupBean.getFunc();
		String name = friendGroupBean.getName();
		if(func == null || name == null)return false;
		
		if(StrUtil.strlen(name) > 12)return false;
		return true;
	}

	public ModifyGroupRspBean getModifyGroupRspBean() {
		return modifyGroupRspBean;
	}

	public void setModifyGroupRspBean(ModifyGroupRspBean modifyGroupRspBean) {
		this.modifyGroupRspBean = modifyGroupRspBean;
	}

}
