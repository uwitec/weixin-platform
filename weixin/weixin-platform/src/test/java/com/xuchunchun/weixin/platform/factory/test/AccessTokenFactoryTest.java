package com.xuchunchun.weixin.platform.factory.test;

import static org.junit.Assert.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xuchunchun.weixin.platform.factory.AccessTokenFactory;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;

public class AccessTokenFactoryTest {

	@Before
	public void init() throws Exception{
		PropertyConfigurator.configure("E:\\code\\weixin\\weixin\\weixin-web\\src\\main\\webapp\\WEB-INF\\config\\log4j.properties");
		
		System.setProperty("platformErrorCodeFile", "/WEB-INF/config/platformErrorCode.properties");
		System.setProperty("platformFile", "/WEB-INF/config/platform.properties");
		System.setProperty("rootLocation", "E:\\code\\weixin\\weixin\\weixin-web\\src\\main\\webapp");

		//微信平台错误码加载
		ErrorCodeFactory.getInstance().init();
		
		//微信平台参数加载
		PlatformParaFactory.getInstance().init();
	}

//	@Test
//	public void testGetAccessToken() throws Exception {
//		System.out.println("test...");
//		String accessToken = AccessTokenFactory.getInstance().getAccessToken();
//		System.out.println("accessToken:["+accessToken+"]");
//	}

}
