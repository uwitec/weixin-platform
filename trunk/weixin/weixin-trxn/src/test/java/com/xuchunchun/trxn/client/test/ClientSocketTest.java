package com.xuchunchun.trxn.client.test;

import static org.junit.Assert.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.xuchunchun.trxn.client.ClientConfig;
import com.xuchunchun.trxn.client.ClientSocket;

public class ClientSocketTest {
	
	@Before
	public void initLog4j(){
		PropertyConfigurator.configure("E:\\code\\weixin\\weixin\\weixin-web\\src\\main\\webapp\\WEB-INF\\config\\log4j.properties");

	}

	@Test
	public void testSend() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setDesc("this is test");
		clientConfig.setEncoding("UTF-8");
		clientConfig.setIp("127.0.0.1");
		clientConfig.setPort(10001);
		clientConfig.setLinger(2);
		clientConfig.setLongConn(true);
		clientConfig.setMaxReConn(5);
		clientConfig.setName("test");
		clientConfig.setReceiveBufferSize(32);
		clientConfig.setSendBufferSize(32);
		clientConfig.setTimeout(5000);
		try {
			ClientSocket client = new ClientSocket(clientConfig);
			System.out.println(new String(client.sendMessage("dddddsadsafdsfd".getBytes())));
			
			
			System.out.println(new String(client.sendMessage("rrrrrrrrrrrrrrrrrrrrrr".getBytes())));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
