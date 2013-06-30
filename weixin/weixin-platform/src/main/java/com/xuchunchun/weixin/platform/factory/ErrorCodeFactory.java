package com.xuchunchun.weixin.platform.factory;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xuchunchun.base.common.BaseConstants;

public class ErrorCodeFactory {
	private static ErrorCodeFactory errorCodeFactory = null;
	private static Logger logger = Logger.getLogger(ErrorCodeFactory.class);
	private static HashMap<String,String> errorCodes = new HashMap<String,String>();
	
	public static ErrorCodeFactory getInstance(){
		if(errorCodeFactory == null)errorCodeFactory = new ErrorCodeFactory();
		return errorCodeFactory;
	}
	
	public String getErrorDesc(String errorCode){
		return errorCodes.get(errorCode);
	}
	
	public String getSuccessCode(){
		return "0";
	}
	
	
	
	public void init() throws Exception{
		logger.info("start to load the error code file....");
		String filePath = System.getProperty(BaseConstants.ROOT_LOCATION)+System.getProperty(BaseConstants.ERRORCODE_PATH);
		
		File file = new File(filePath);
		Properties systemProperties = new Properties();
		
		systemProperties.load(new FileInputStream(file));
		
		logger.info("read the error code:");
		
		for(Map.Entry<Object, Object> entry : systemProperties.entrySet()){
			logger.info("add the error code:"+entry.getKey()+":["+entry.getValue()+"] into the cache");
			errorCodes.put(entry.getKey().toString(), entry.getValue().toString());
		}
		
	}
	
}
