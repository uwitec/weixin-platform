package com.xuchunchun.weixin.platform.process.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.xuchunchun.weixin.platform.msg.req.TextMsgRequest;
import com.xuchunchun.weixin.platform.process.PushRequestMsgProcess;

public class PushRequestMsgProcessTest {

	@Test
	public void testParseXmlToObj() {
		String xmlStr = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		try {
			TextMsgRequest text = (TextMsgRequest)PushRequestMsgProcess.parseXmlToObj(xmlStr);
			System.out.println(text.getFromUserName());
			System.out.println(text.getToUserName());
			System.out.println(text.getCreateTime().toLocaleString());
			System.out.println(text.getMsgType());
			System.out.println(text.getContent());
			System.out.println(text.getMsgId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
