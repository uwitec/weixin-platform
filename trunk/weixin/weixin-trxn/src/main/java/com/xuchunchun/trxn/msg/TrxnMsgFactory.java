package com.xuchunchun.trxn.msg;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.xuchunchun.trxn.msg.cfg.Field;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.util.FillValueUtil;
import com.xuchunchun.trxn.util.TrxnMessageUtil;

public class TrxnMsgFactory {
	private static TrxnMsgFactory trxnMsgFactory = null;
	private static Logger logger = Logger.getLogger(TrxnMsgFactory.class);
	private HashMap<String ,TrxnMessage> requestMessage = new HashMap<String,TrxnMessage>();
	private HashMap<String ,TrxnMessage> responseMessage = new HashMap<String,TrxnMessage>();

	public HashMap<String ,TrxnMessage> getRequestMessage(){
		return requestMessage;
	}
	
	public HashMap<String ,TrxnMessage> getResponseMessage(){
		return responseMessage;
	}
	
	public static TrxnMsgFactory getInstance(){
		if(trxnMsgFactory == null)trxnMsgFactory = new TrxnMsgFactory();
		return trxnMsgFactory;
	}
	
	public void addReqTrxnMessage(String name,TrxnMessage message){
		requestMessage.put(name, message);
	}
	
	public void addRspTrxnMessage(String name,TrxnMessage message){
		responseMessage.put(name, message);
	}
	
	public TrxnMessage getReqMessage(String name){
		return requestMessage.get(name);
	}
	
	public TrxnMessage getRspMessage(String name){
		return responseMessage.get(name);
	}
	
	public TrxnMessage getReqMessageClone(String name){
		return requestMessage.get(name).clone();
	}
	
	public TrxnMessage getRspMessageClone(String name){
		return responseMessage.get(name).clone();
	}
	
	
	public void init(){
		logger.info("start to init the message object....");
		HashMap<String,TrxnConfig> trxnConfigs = TrxnMsgConfigFactory.getInstance().getTrxnConfigs();
		
		if(trxnConfigs != null && trxnConfigs.size() > 0){
			for(Map.Entry<String,TrxnConfig> trxnConfig : trxnConfigs.entrySet()){
				String name = trxnConfig.getKey();
				TrxnConfig _trxnConfig = trxnConfig.getValue();
				addReqTrxnMessage(name,TrxnMessageUtil.genRequestMsgObj(_trxnConfig));
				addRspTrxnMessage(name,TrxnMessageUtil.genResponseMsgObj(_trxnConfig));
			}
		}
		
	}
}
