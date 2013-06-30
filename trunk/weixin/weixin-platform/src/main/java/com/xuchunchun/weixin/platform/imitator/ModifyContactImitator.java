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
import com.xuchunchun.weixin.platform.bean.ModifyContactBean;
import com.xuchunchun.weixin.platform.bean.ModifyContactRspBean;
import com.xuchunchun.weixin.platform.bean.PlatformMsgRspBean;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;

public class ModifyContactImitator extends WebSiteImitator {
	private Logger logger = Logger.getLogger(ModifyContactImitator.class);

	private ModifyContactBean modifyContactBean = null;
	
	private ModifyContactRspBean modifyContactRspBean = null;
		
	private boolean result = false;
	
	public ModifyContactImitator(ModifyContactBean friendGroupBean){
		this.modifyContactBean = modifyContactBean;
	}
	
	public ModifyContactImitator(int groupId,String ... fakeId){
		modifyContactBean = new ModifyContactBean();
		modifyContactBean.setGroupId(groupId);
		for(String _fakeId : fakeId){
			modifyContactBean.addFakeId(_fakeId);
		}
    }
	

	@Override
	protected String getRspText() throws Exception {
		logger.info("send the modify contact message...");
    	
		modifyContactBean.setToken(token);
		
		try {
        	
        	String url = platformParaFactory.getParameter(PlatformConstants.MODIFY_CONTACTS);
        	String refererUrl = platformParaFactory.getParameter(PlatformConstants.INDEX_URL);
        	
        	logger.info("send the modify contact message, the url is "+url);
            
        	PostMethod post = new PostMethod(url);
            post.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
            post.setRequestHeader(PlatformConstants.REFERER_H, refererUrl);
            post.setRequestHeader("Cookie", this.cookieStr);
            Part[] parts = null;
            parts = new Part[]{
                    new StringPart("token", modifyContactBean.getToken()),
                    new StringPart("tofakeidlist", modifyContactBean.getFackListStr()),
                    new StringPart("contacttype", String.valueOf(modifyContactBean.getGroupId()))};
            
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
		modifyContactRspBean = JsonUtil.parseToBean(rspText, ModifyContactRspBean.class);
    	
    	if(ErrorCodeFactory.getInstance().getSuccessCode().equals(modifyContactRspBean.getRet())){
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
		
		return true;
	}
	
	public ModifyContactRspBean getModifyContactRspBean() {
		return modifyContactRspBean;
	}

	public void setModifyContactRspBean(ModifyContactRspBean modifyContactRspBean) {
		this.modifyContactRspBean = modifyContactRspBean;
	}

}
