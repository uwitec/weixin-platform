package com.xuchunchun.weixin.platform.util.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.xuchunchun.weixin.platform.msg.req.EventMsgRequest;
import com.xuchunchun.weixin.platform.util.PlatformUtil;

public class PlatformUtilTest {

	@Before
	public void before(){
		System.setProperty("event_key", "[Event]_[EventKey]");
		
	}
	
	@Test
	public void testGetMsgKey() {
		EventMsgRequest msg = new EventMsgRequest();
		msg.setCreateTime(new Date());
		msg.setFromUserName("dddd");
		msg.setMsgType("event");
		msg.setToUserName("ddddd");
		msg.setEvent("sdfsaf");
		msg.setEventKey("dddddddddddddddddddddddd");
		
		try {
			System.out.println(PlatformUtil.getMsgKey(msg));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

}
