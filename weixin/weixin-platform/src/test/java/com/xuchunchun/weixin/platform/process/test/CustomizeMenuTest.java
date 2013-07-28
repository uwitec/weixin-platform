package com.xuchunchun.weixin.platform.process.test;

import static org.junit.Assert.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.process.CustomizeMenuProcess;

public class CustomizeMenuTest {

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
	
	//@Test
	public void testCreateMenu() throws Exception {
		String s = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"click\",\"name\":\"hello word\",\"key\":\"V1001_HELLO_WORLD\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";

		CustomizeMenuProcess.createMenu(s);
	}

}
