package com.xuchunchun.weixin.platform.imitator;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessException;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.UserListImitatorBean;

public class UserListImitator extends WebSiteImitator {
	private Logger logger = Logger.getLogger(UserListImitator.class);
	
	private UserListImitatorBean userListBean = null;
	
	public UserListImitator(int pageSize,int pageidx,int type,int groupId,String keyword){
		userListBean = new UserListImitatorBean();
		userListBean.setPageSize(pageSize);
		userListBean.setPageidx(pageidx);
		userListBean.setType(type);
		userListBean.setGroupId(groupId);
		userListBean.setKeyword(keyword);
	}
	
	public UserListImitator(){
		userListBean = new UserListImitatorBean();
	}
	
	public UserListImitator(int pageSize){
		userListBean = new UserListImitatorBean();
		userListBean.setPageSize(pageSize);
	}
	

	@Override
	protected String getRspText() throws Exception {
		try {
			userListBean.setToken(token);
        	String url = platformParaFactory.getParameter(PlatformConstants.USER_LIST_URL);
        	
        	logger.info("get the counts of fans, the url is "+url);
            
        	GetMethod get = new GetMethod(url);
            get.setRequestHeader(PlatformConstants.REFERER_H, PlatformConstants.INDEX_URL);
            get.setRequestHeader("Cookie", this.cookieStr);
            NameValuePair[] params = new NameValuePair[]{
                    new NameValuePair("t", "wxm-friend"),
                    new NameValuePair("lang", "zh_CN"),
                    new NameValuePair("token", userListBean.getToken()),
                    new NameValuePair("pagesize", String.valueOf(userListBean.getPageSize())),
                    new NameValuePair("pageidx", String.valueOf(userListBean.getPageidx())),
                    new NameValuePair("type", String.valueOf(userListBean.getType())),
                    new NameValuePair("keyword", URIUtil.encodeQuery(userListBean.getKeyword())),
                    new NameValuePair("groupid", String.valueOf(userListBean.getGroupId()))};
            get.setQueryString(params);
            System.out.println(get.getURI());
            int status = client.executeMethod(get);
            if (status == HttpStatus.SC_OK) {
            	String ret = get.getResponseBodyAsString();
            	logger.info("the reponse text is :\n"+ret);
                return ret;
            }
            return null;
        } catch (Exception e) {
            logger.info("error of getting the count of fans..");
            return null;
        }
	}

	@Override
	protected void processRspText(String rspText) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean doCheck(){
		return true;
	}
	
	

}
