package com.xuchunchun.trxn.msg.test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.trxn.msg.TrxnMessage;
import com.xuchunchun.trxn.msg.TrxnMsgConfigFactory;
import com.xuchunchun.trxn.msg.TrxnMsgFactory;

public class TrxnMsgFactoryTest extends TestCase {

	@Before
	public void before(){
		System.setProperty(BaseConstants.ROOT_LOCATION, "E:\\code\\weixin\\weixin\\weixin-web\\src\\main\\webapp");
		System.setProperty(BaseConstants.TRXN_CONFIG_PATH, "\\WEB-INF\\config\\trxn");
	}
	
	@Test
	public void testInit(){
		System.setProperty(BaseConstants.ROOT_LOCATION, "E:\\code\\weixin\\weixin\\weixin-web\\src\\main\\webapp");
		System.setProperty(BaseConstants.TRXN_CONFIG_PATH, "\\WEB-INF\\config\\trxn");
		try {
			TrxnMsgConfigFactory.getInstance().init();
			
			TrxnMsgFactory.getInstance().init();

			HashMap<String ,TrxnMessage> requestMessages = TrxnMsgFactory.getInstance().getRequestMessage();
			
			HashMap<String ,TrxnMessage> responseMessages = TrxnMsgFactory.getInstance().getResponseMessage();
			
			for(Map.Entry<String ,TrxnMessage> requestMessage : requestMessages.entrySet()){
				System.out.println("start name:["+requestMessage.getKey()+"]-----------------------------");
				
				TrxnMessage message = requestMessage.getValue();
				
				HashMap<String ,Object> msgs = message.getMessage();
				
				for(Map.Entry<String ,Object> msg : msgs.entrySet()){
					Object o = msg.getValue();
					System.out.println(msg.getKey()+":"+(o==null?"null":o.toString()));
					
				}
				
				
				System.out.println("end name:["+requestMessage.getKey()+"]-----------------------------");

			}
			
			System.out.println("****************************************************");
			
			for(Map.Entry<String ,TrxnMessage> responseMessage : responseMessages.entrySet()){
				System.out.println("start name:["+responseMessage.getKey()+"]-----------------------------");
				
				TrxnMessage message = responseMessage.getValue();
				
				HashMap<String ,Object> msgs = message.getMessage();
				
				for(Map.Entry<String ,Object> msg : msgs.entrySet()){
					System.out.println(msg.getKey()+":"+(msg.getValue()==null?"null":msg.getValue().toString()));
					
				}
				
				
				System.out.println("end name:["+responseMessage.getKey()+"]-----------------------------");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
