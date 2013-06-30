package com.xuchunchun.weixin.platform.process.test;

import static org.junit.Assert.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xuchunchun.weixin.platform.bean.FriendGroupBean.GroupFunc;
import com.xuchunchun.weixin.platform.bean.SingleMsgSendBean.MsgType;
import com.xuchunchun.weixin.platform.factory.AccessTokenFactory;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.imitator.ModifyContactImitator;
import com.xuchunchun.weixin.platform.imitator.ModifyGroupImitator;
import com.xuchunchun.weixin.platform.imitator.SingleMsgSendImitator;
import com.xuchunchun.weixin.platform.imitator.UserListImitator;
import com.xuchunchun.weixin.platform.imitator.WebSiteImitator;

public class WebSiteImitatorTest {

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
	public void testModifyGroup() throws Exception {
		ModifyGroupImitator imitator = new ModifyGroupImitator(GroupFunc.delete,"色名单",106);
		imitator.doProcess();
	}
	
	//@Test
	public void testModifyContact() throws Exception {
		ModifyContactImitator imitator = new ModifyContactImitator(105,"1450542700","832787760");
		imitator.doProcess();
	}

//	//@Test
//	public void testLogin() throws Exception {
//		WebSiteImitator imitator = new WebSiteImitator();
//		imitator.login();
//	}
//	
	@Test
	public void testGetUsers() throws Exception {
		UserListImitator imitator = new UserListImitator(10,0,0,0,"hanhenyinpishuang");
		//UserListImitator imitator = new UserListImitator(10,0,3,0,"徐浩");

		imitator.doProcess();
	}
	
//	
	//@Test
	public void testSendSingleMsg() throws Exception {
		SingleMsgSendImitator imitator = new SingleMsgSendImitator("测试测试", "1783758800", MsgType.TEXT);
		imitator.doProcess();
	}	
}
