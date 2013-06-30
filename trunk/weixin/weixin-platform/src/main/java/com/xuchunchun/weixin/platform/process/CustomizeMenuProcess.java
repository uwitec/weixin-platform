package com.xuchunchun.weixin.platform.process;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessException;
import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.CustomizeMenuBean;
import com.xuchunchun.weixin.platform.bean.ReponseMsgBean;
import com.xuchunchun.weixin.platform.factory.AccessTokenFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;

public class CustomizeMenuProcess {
	private static Logger logger = Logger.getLogger(CustomizeMenuProcess.class);

	private static <T> T doRequest(Class<T> clazz,String url,String type ,String menuJson) throws Exception{
		logger.info("do the prePost task...");
		
		HttpClient client = new HttpClient();
		
		logger.info("get the http method...");
		
		String accessToken = AccessTokenFactory.getInstance().getAccessToken();

		logger.info("get the access token :"+accessToken);
		
		if(accessToken == null)
			throw new BusinessLogicException("009", "");
		
		HttpMethod method = null;
		
		if("POST".equals(type)){
			method = new PostMethod(String.format(url, accessToken));
			logger.info("do the packege the message : "+menuJson);
			RequestEntity entity = new StringRequestEntity(menuJson,PlatformConstants.TEXT_OR_XML, PlatformConstants.UTF_8);
			((PostMethod)method).setRequestEntity(entity);

		}else{
			method = new GetMethod(String.format(url, accessToken));

		}
		
		method.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
		
		int status = client.executeMethod(method);
		
		if (status == HttpStatus.SC_OK) {
            String ret = method.getResponseBodyAsString();
            method.releaseConnection();
            
            logger.info("the response message is :"+ret);
            
            return JsonUtil.parseToBean(ret,clazz);
		}else{
			method.releaseConnection();

			logger.error("request error,error code is "+status);
			throw new BusinessLogicException("008", status);

		}
        
        
	}
	
	public static ReponseMsgBean createMenu(String menuJson) throws Exception{
		logger.info("start to create the menu...");
		
		String url = PlatformParaFactory.getInstance().getParameter(PlatformConstants.CREATE_MENU_URL);
		
		return doRequest(ReponseMsgBean.class,url,"POST",menuJson);
	} 
	
	public static CustomizeMenuBean queryMenu() throws Exception{
		logger.info("start to query the menu...");
		
		String url = PlatformParaFactory.getInstance().getParameter(PlatformConstants.QUERY_MENU_URL);
		
		return doRequest(CustomizeMenuBean.class,url,"GET",null);

	}
	
	public static ReponseMsgBean deleteMenu() throws Exception{
		logger.info("start to delete the menu...");
		
		String url = PlatformParaFactory.getInstance().getParameter(PlatformConstants.DELETE_MENU_URL);
		
		return doRequest(ReponseMsgBean.class,url,"GET",null);

	}

}
