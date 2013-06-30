package com.xuchunchun.trxn.client;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.trxn.util.ProtocolConfigUtil;


public class ClientConfigFactory {
	private static Logger logger = Logger.getLogger(ClientConfigFactory.class);
	private static ClientConfigFactory clientConfigFactory = null;
	private static HashMap<String,ClientConfig> clientConfigs = new HashMap<String,ClientConfig>();
	private static final String XML_ELE_CLIENT = "client";
	private static final String XML_ELE_NAME = "name";
	private static final String XML_ELE_DESC = "desc";
	private static final String XML_ELE_IP = "ip";
	private static final String XML_ELE_PORT = "port";
	private static final String XML_ELE_TIMEOUT = "timeout";
	private static final String XML_ELE_ENCODING = "encoding";
	private static final String XML_ELE_LONGCONN = "longConn";
	private static final String XML_ELE_LINGER = "linger";
	private static final String XML_ELE_SENDBUFFERSIZE = "sendBufferSize";
	private static final String XML_ELE_RECEIVEBUFFERSIZE = "receiveBufferSize";
	private static final String XML_ELE_MAXRECONN = "maxReConn";
	private static final String XML_ELE_PROTOCOLCLASS = "protocolClass";
	private static final String XML_ELE_PROTOCOLCONFIG = "protocolConfig";

	
	public static ClientConfigFactory getInstance(){
		if(clientConfigFactory == null){
			clientConfigFactory = new ClientConfigFactory();
		}
		return clientConfigFactory;
	}
	
	public HashMap<String,ClientConfig> getAllClientConfigs(){
		return clientConfigs;
	}
	
	public ClientConfig getClientConfigByName(String name){
		return clientConfigs.get(name);
	}
	
	public void refresh() throws Exception{
		clientConfigs.clear();
		init();
	}
	
	public void init() throws Exception{
		logger.info("start to load the all client config data to system");
		String rootPath = System.getProperty(BaseConstants.ROOT_LOCATION);
		String clientConfigPath = rootPath+System.getProperty(BaseConstants.CLIENT_CONFIG_PATH);
		
		File clientConfigFile = new File(clientConfigPath);
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(clientConfigFile);
		
		Element root = doc.getRootElement();
		
		List<Element> clientElements = root.elements(XML_ELE_CLIENT);
		
		for(Element clientElement : clientElements){
			ClientConfig clientConfig = new ClientConfig();
			
			String name = clientElement.elementText(XML_ELE_NAME);
			String desc = clientElement.elementText(XML_ELE_DESC);
			String ip = clientElement.elementText(XML_ELE_IP);
			int port = Integer.parseInt(clientElement.elementText(XML_ELE_PORT));
			int timeout = Integer.parseInt(clientElement.elementText(XML_ELE_TIMEOUT));
			String encoding = clientElement.elementText(XML_ELE_ENCODING);
			boolean longConn = Boolean.parseBoolean(clientElement.elementText(XML_ELE_LONGCONN));
			int linger = Integer.parseInt(clientElement.elementText(XML_ELE_LINGER));
			int sendBufferSize = Integer.parseInt(clientElement.elementText(XML_ELE_SENDBUFFERSIZE));
			int receiveBufferSize = Integer.parseInt(clientElement.elementText(XML_ELE_RECEIVEBUFFERSIZE));
			int maxReConn = Integer.parseInt(clientElement.elementText(XML_ELE_MAXRECONN));
			String protocolClass = clientElement.elementText(XML_ELE_PROTOCOLCLASS);
			String protocolConfig = clientElement.elementText(XML_ELE_PROTOCOLCONFIG);

			
			logger.info("name:"+name);
			logger.info("desc:"+desc);
			logger.info("ip:"+ip);
			logger.info("port:"+port);
			logger.info("timeout:"+timeout);
			logger.info("encoding:"+encoding);
			logger.info("longConn:"+longConn);
			logger.info("linger:"+linger);
			logger.info("sendBufferSize:"+sendBufferSize);
			logger.info("receiveBufferSize:"+receiveBufferSize);
			logger.info("maxReConn:"+maxReConn);
			logger.info("protocolClass:"+protocolClass);
			logger.info("protocolConfig:"+protocolConfig);


			clientConfig.setName(name);
			clientConfig.setDesc(desc);
			clientConfig.setIp(ip);
			clientConfig.setPort(port);
			clientConfig.setTimeout(timeout);
			clientConfig.setEncoding(encoding);
			clientConfig.setLongConn(longConn);
			clientConfig.setLinger(linger);
			clientConfig.setSendBufferSize(sendBufferSize);
			clientConfig.setReceiveBufferSize(receiveBufferSize);
			clientConfig.setMaxReConn(maxReConn);
			clientConfig.setProtocolClass(protocolClass);
			clientConfig.setProtocolConfig(protocolConfig);
			
			logger.info("put the config data into the cache:"+clientConfig.getName());
			
			
			//加载协议配置
			logger.info("load the protocol config....");
			String protocolPath = rootPath + clientConfig.getProtocolConfig();
			
			File fileDir = new File(protocolPath);
			if(fileDir.isDirectory()){
				File[] files = fileDir.listFiles();
				
				for(File file : files){
					clientConfig.addProtocolConfigObjs(ProtocolConfigUtil.parseConfigData(file));
				}
			}
			
			clientConfigs.put(clientConfig.getName(), clientConfig);

		}
	}
	
	
}
