package com.xuchunchun.trxn.msg;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.util.TrxnMessageUtil;

public class NamedSqlFactory {
	private static NamedSqlFactory namedSqlFactory = null;
	private static Logger logger = Logger.getLogger(NamedSqlFactory.class);
	private static HashMap<String,String> namedSqls = new HashMap<String,String>();
	
	public static NamedSqlFactory getInstance(){
		if(namedSqlFactory == null)namedSqlFactory = new NamedSqlFactory();
		return namedSqlFactory;
	}
	
	public String getNamedSql(String key){
		return namedSqls.get(key);
	}
	
	
	public void init() throws Exception{
		logger.info("start to init the named sql....");
		String filePath = System.getProperty(BaseConstants.ROOT_LOCATION)+System.getProperty(BaseConstants.NAMEDSQL_PATH);
		
		File file = new File(filePath);
		Properties systemProperties = new Properties();
		
		logger.info("load the named sql into the cache.....");
		
		systemProperties.load(new FileInputStream(file));
		
		logger.info("read the parameters:");
		
		for(Map.Entry<Object, Object> entry : systemProperties.entrySet()){
			logger.info("add the parameter:"+entry.getKey()+":["+entry.getValue()+"] into the cache");
			namedSqls.put(entry.getKey().toString(), entry.getValue().toString());
		}
		
	}
}
