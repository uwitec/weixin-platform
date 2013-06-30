package com.xuchunchun.weixin.platform.process.test;

import java.util.ArrayList;

import org.junit.Test;

import com.xuchunchun.base.util.PWD;
import com.xuchunchun.weixin.platform.msg.ApplicationMsg;
import com.xuchunchun.weixin.platform.process.AppMsgCheck;

import junit.framework.TestCase;

public class AppMsgCheckTest extends TestCase {
	
	@Test
	public void testDictSort(){
		ApplicationMsg msg = new ApplicationMsg();
		msg.setNonce("sdadad");
		msg.setToken(PWD.enc("sfhfhdh"));
		msg.setTimestamp("tytryutyu");
		
		ArrayList<String> m = AppMsgCheck.dictSort(msg);
		
		System.out.println(m.size());
		
		for(String s : m){
			System.out.println(s);
		}
	}
	
	
	@Test
	public void testEncBySha1() throws Exception{
		ApplicationMsg msg = new ApplicationMsg();
		msg.setNonce("sdadad");
		msg.setToken(PWD.enc("sfhfhdh"));
		msg.setTimestamp("tytryutyu");
		
		ArrayList<String> m = AppMsgCheck.dictSort(msg);
		
		System.out.println(AppMsgCheck.encBySha1(m));
	}
	
}
