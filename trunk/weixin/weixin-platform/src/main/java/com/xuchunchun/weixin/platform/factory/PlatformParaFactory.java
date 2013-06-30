package com.xuchunchun.weixin.platform.factory;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xuchunchun.base.common.BaseConstants;

public class PlatformParaFactory {
	private static PlatformParaFactory platformParaFactory = null;
	private static Logger logger = Logger.getLogger(PlatformParaFactory.class);
	private static HashMap<String,String> parameters = new HashMap<String,String>();
	
	public static PlatformParaFactory getInstance(){
		if(platformParaFactory == null)platformParaFactory = new PlatformParaFactory();
		return platformParaFactory;
	}
	
	public String getParameter(String key){
		return parameters.get(key);
	}
	
	
	public void init() throws Exception{
		logger.info("start to load the parameters file....");
		String filePath = System.getProperty(BaseConstants.ROOT_LOCATION)+System.getProperty(BaseConstants.PLATFORM_PATH);
		
		File file = new File(filePath);
		Properties systemProperties = new Properties();
		
		systemProperties.load(new FileInputStream(file));
		
		logger.info("read the parameters:");
		
		for(Map.Entry<Object, Object> entry : systemProperties.entrySet()){
			logger.info("add the parameter:"+entry.getKey()+":["+entry.getValue()+"] into the cache");
			parameters.put(entry.getKey().toString(), entry.getValue().toString());
		}
		
	}
	
}
