package com.xuchunchun.trxn.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ClientSocketFactory {
	private static Logger logger = Logger.getLogger(ClientSocketFactory.class);

	private static ClientSocketFactory clientSocketFactory = null;
	
	private static HashMap<String,ClientSocket> ClientSockets = new HashMap<String,ClientSocket>();
	
	public static ClientSocketFactory getInstance(){
		if(clientSocketFactory == null)clientSocketFactory = new ClientSocketFactory();
		return clientSocketFactory;
	}
	
	public void init() throws Exception{
		logger.info("init the client socket factory....");
		HashMap<String,ClientConfig> clientConfigs = ClientConfigFactory.getInstance().getAllClientConfigs();
		
		for(Map.Entry<String,ClientConfig> clientConfigSet : clientConfigs.entrySet()){
			ClientConfig clientConfig = clientConfigSet.getValue();
			String name = clientConfigSet.getKey();
			ClientSocket clientSocket = new ClientSocket(clientConfig);
			clientSocket.init();
			if(clientConfig.isLongConn())clientSocket.connect();
			
			ClientSockets.put(name, clientSocket);
		}
	}
	
	public ClientSocket getClientSocket(String name) throws Exception{
		logger.info("get the client socket with name:"+name);
		if(ClientSockets.containsKey(name))return ClientSockets.get(name);
		else{
			ClientConfig clientConfig = ClientConfigFactory.getInstance().getClientConfigByName(name);

			ClientSocket clientSocket = new ClientSocket(clientConfig);
			clientSocket.init();
			if(clientConfig.isLongConn())clientSocket.connect();
			ClientSockets.put(name, clientSocket);
			return clientSocket;
		}
	}
	 
}
