package com.xuchunchun.weixin.platform.process;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.AccessTokenBean;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.util.ErrorCodeUtil;

public class AccessTokenProcess extends CommonRequestProcess {
	private Logger logger = Logger.getLogger(AccessTokenProcess.class);
	private static String ACCESS_TOKEN_GRANT_TYPE = "client_credential";
	
	public AccessTokenBean getAccessTokenBean() throws Exception{
		logger.info("get the access token");
		return doRequest(AccessTokenBean.class,RequestType.GET);
	}

	@Override
	protected String formatUrl() throws Exception {
		PlatformParaFactory platformParaFactory = PlatformParaFactory.getInstance();
		String commonUrl = platformParaFactory.getParameter(PlatformConstants.COMMON_URL);
		String appid = platformParaFactory.getParameter(PlatformConstants.APPID);
		String secret = platformParaFactory.getParameter(PlatformConstants.SECRET);
		return String.format(commonUrl, ACCESS_TOKEN_GRANT_TYPE,appid,secret);
	}

	@Override
	protected void setRequestPara(HttpMethod method) throws Exception {
		
		
	}

}
