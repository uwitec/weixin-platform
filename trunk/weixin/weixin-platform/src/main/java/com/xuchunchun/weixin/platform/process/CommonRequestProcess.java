package com.xuchunchun.weixin.platform.process;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.AccessTokenBean;
import com.xuchunchun.weixin.platform.bean.ReponseMsgBean;
import com.xuchunchun.weixin.platform.factory.AccessTokenFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.util.ErrorCodeUtil;

public abstract class CommonRequestProcess {
	private Logger logger = Logger.getLogger(CommonRequestProcess.class);

	public enum RequestType{
		POST,GET;
	};
	
	private ReponseMsgBean errorBean = null;
	
	public String getAccessToken() throws Exception{

		String accessToken = AccessTokenFactory.getInstance().getAccessToken();

		logger.info("get the access token :"+accessToken);
		
		if(accessToken == null)
			throw new BusinessLogicException("009", "");
		
		return accessToken;
	}
	
	
	protected abstract String formatUrl() throws Exception;
	protected abstract void setRequestPara(HttpMethod method) throws Exception;

	
	public <T> T doRequest(Class<T> clazz,RequestType type) throws Exception{
		HttpClient client = new HttpClient();
		HttpMethod method = null;
		logger.info("do the prepare task ...");
		String url = formatUrl();
		
		logger.info("the request url is "+url);
		switch(type){
		case POST:{
			method = new PostMethod(url);
			break;
		}
		case GET:{
			method = new GetMethod();
			break;
		}
		}
		
		logger.info("set the request parameters...");
		setRequestPara(method);
		
		int status = client.executeMethod(method);
		
		
		if(status == HttpStatus.SC_OK){
			String ret = method.getResponseBodyAsString();
			method.releaseConnection();

			logger.info("the response message is :"+ret);
			errorBean = JsonUtil.parseToBean(ret, ReponseMsgBean.class);
			
			if(errorBean.getErrcode() != null){
				return null;
			}else{
				return JsonUtil.parseToBean(ret, clazz);
			}
		}else{
			method.releaseConnection();

			logger.error("request error,error code is "+status);
			throw new BusinessLogicException("008", status);

		}
		
		
	}


	public ReponseMsgBean getErrorBean() {
		return errorBean;
	}


	public void setErrorBean(ReponseMsgBean errorBean) {
		this.errorBean = errorBean;
	}

}
