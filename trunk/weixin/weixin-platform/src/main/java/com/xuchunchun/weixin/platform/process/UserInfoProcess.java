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
import com.xuchunchun.weixin.platform.bean.UserInfoBean;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.util.ErrorCodeUtil;

public class UserInfoProcess extends CommonRequestProcess {
	private Logger logger = Logger.getLogger(UserInfoProcess.class);
	private String openId = "";
	
	public UserInfoProcess(String openId){
		this.openId = openId;
	}
	
	public UserInfoBean getUserInfo() throws Exception{
		logger.info("get the user info");
		return doRequest(UserInfoBean.class,RequestType.GET);
	}

	@Override
	protected String formatUrl() throws Exception {
		PlatformParaFactory platformParaFactory = PlatformParaFactory.getInstance();
		String commonUrl = platformParaFactory.getParameter(PlatformConstants.USER_INFO);
		
		return String.format(commonUrl, getAccessToken(),openId);
	}

	@Override
	protected void setRequestPara(HttpMethod method) throws Exception {
		
		
	}

}
