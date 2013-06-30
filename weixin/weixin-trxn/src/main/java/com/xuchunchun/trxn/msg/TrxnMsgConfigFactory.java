package com.xuchunchun.trxn.msg;

import java.io.File;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.util.TrxnConfigUtil;

public class TrxnMsgConfigFactory {
	private static TrxnMsgConfigFactory trxnMsgConfigFactory = null;
	private static Logger logger = Logger.getLogger(TrxnMsgConfigFactory.class);
	private HashMap<String,TrxnConfig> trxnConfigs = new HashMap<String,TrxnConfig>();
	
	public static TrxnMsgConfigFactory getInstance(){
		if(trxnMsgConfigFactory == null)trxnMsgConfigFactory = new TrxnMsgConfigFactory();
		return trxnMsgConfigFactory;
	}
	
	public HashMap<String,TrxnConfig> getTrxnConfigs(){
		return trxnConfigs;
	}
	
	public TrxnConfig getTrxnConfigByName(String name){
		return trxnConfigs.get(name);
	}
	
	public void addTrxnConfig(String trxnName,TrxnConfig trxnConfig){
		trxnConfigs.put(trxnName, trxnConfig);
	}
	
	public void init() throws Exception{
		logger.info("start to init the transaction config parameters..");
		String rootPath = System.getProperty(BaseConstants.ROOT_LOCATION);
		String location = rootPath+System.getProperty(BaseConstants.TRXN_CONFIG_PATH);
		logger.info("the location is "+location);
		File fileDir = new File(location);
		if(fileDir.isDirectory()){
			File[] files = fileDir.listFiles();
			
			for(File file : files){
				if(!file.isFile())continue;
				logger.info("start to parse the file:"+file.getName());
				TrxnConfig trxnConfig = TrxnConfigUtil.parseConfigData(file);
				
				
				
				addTrxnConfig(trxnConfig.getTrxnName(),trxnConfig);
			}
		}

	}
}
