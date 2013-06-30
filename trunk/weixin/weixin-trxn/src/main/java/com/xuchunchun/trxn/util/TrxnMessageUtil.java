package com.xuchunchun.trxn.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EnumType;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.apache.log4j.Logger;

import com.lowagie.text.pdf.events.IndexEvents.Entry;
import com.xuchunchun.trxn.msg.TrxnMessage;
import com.xuchunchun.trxn.msg.cfg.Field;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;

public class TrxnMessageUtil {
	private static Logger logger = Logger.getLogger(TrxnMessageUtil.class);
	public static enum TYPE{
		S,B,L,D
	};
	
	public static TrxnMessage genRequestMsgObj(TrxnConfig config){
		return genMessageObj(config.getRequestFields().getFields());
	}
	
	public static TrxnMessage genResponseMsgObj(TrxnConfig config){
		return genMessageObj(config.getResponseFields().getFields());
	}
	
	private static TrxnMessage genMessageObj(HashMap<String,Field> fields){
		logger.info("start to generate a transaction object from a list of field parameters...");
		TrxnMessage message = new TrxnMessage();		
		for(Map.Entry<String, Field> entry : fields.entrySet()){
			String name = entry.getKey();
			Field field = entry.getValue();
			
			if(!field.isMulty()){
				message.setSingleObj(name);
			}else{
				TrxnMessage subTrxnMessage = genMessageObj(field.getFields());
				message.setMultyObj(name,subTrxnMessage);
			}
		}
		
		
		return message;
	}
	
	public static boolean checkRspMsg(String expr,String val,boolean isNull){
		if((val == null || "".equals(val)) && !isNull)return false;
		
		Pattern pattern = Pattern.compile(expr);
		Matcher matcher = pattern.matcher(val);
		return matcher.matches();
	}
	
	public static String changeObjToStr(Object obj,String dateFormat){
		if(obj == null)return "";
		
		if(obj instanceof BigDecimal){
			return obj.toString();
		}else if(obj instanceof String){
			return obj.toString();
		}else if(obj instanceof Long){
			return obj.toString();
		}else if(obj instanceof java.sql.Date || obj instanceof java.util.Date){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
			return simpleDateFormat.format(obj);
		}else return obj.toString();
	}
	
	public static Object changeStrToObj(String type,String dateFormat,String val) throws Exception{
		switch(EnumType.valueOf(TYPE.class, type)){
			case S:{
				return val;
			}
			case B:{
				return new BigDecimal(val.trim());
			}
			case L:{
				return Long.valueOf(val.trim());
			}
			case D:{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
				return simpleDateFormat.parse(val);
			}
			default: return null;
		}
	}

}
