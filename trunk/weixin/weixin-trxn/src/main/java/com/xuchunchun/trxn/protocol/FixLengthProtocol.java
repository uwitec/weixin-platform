package com.xuchunchun.trxn.protocol;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.EnumType;

import org.apache.log4j.Logger;

import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.trxn.msg.TrxnMessage;
import com.xuchunchun.trxn.msg.TrxnMsgFactory;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.protocol.cfg.Field;
import com.xuchunchun.trxn.protocol.cfg.ProtocolConfig;
import com.xuchunchun.trxn.util.TrxnMessageUtil;
import com.xuchunchun.trxn.util.TrxnMessageUtil.TYPE;

public class FixLengthProtocol implements BaseProtocol {

	private static Logger logger = Logger.getLogger(FixLengthProtocol.class);

	@Override
	public byte[] pack(TrxnMessage msgObj, ProtocolConfig protocolConfig,
			String encoding, TrxnConfig trxnConfig) throws Exception {
		logger.info("start to package the message....");
		
		StringBuffer message = new StringBuffer();

		ArrayList<Field> protocolFields = protocolConfig.getRequestFields();

		if (protocolFields == null)
			throw new Exception("please load the protocol config files .....");
		
		HashMap<String,com.xuchunchun.trxn.msg.cfg.Field> trxnFields = trxnConfig.getRequestFields().getFields();

		if (trxnFields == null)
			throw new Exception("please load the trxn config files .....");

		message.append(_pack(msgObj,protocolFields,trxnFields));

		logger.info("change the string to byte array...");
		
		byte [] msg = message.toString().getBytes(encoding);

		logger.info("finish the package process....");

		return msg;
	}
	
	public String _pack(TrxnMessage msgObj, ArrayList<Field> protocolFields,
			HashMap<String,com.xuchunchun.trxn.msg.cfg.Field> trxnFields) throws Exception {
		logger.info("start to package the message....");
		
		StringBuffer message = new StringBuffer();

		for (int i = 0; i < protocolFields.size(); i++) {
			Field protocolField = protocolFields.get(i);
			String name = protocolField.getName();
			com.xuchunchun.trxn.msg.cfg.Field trxnField = trxnFields.get(name);
			logger.info("process the " + (i+1) + "th field, field name is " + name);
			
			if(trxnField == null)
				throw new Exception("the transaction config file is not match with the protocol file..");
			
			if(!trxnField.isMulty()){
				Object fieldObj = msgObj.getSingleObj(name);
				message.append(formatField(protocolField,trxnField,fieldObj));
			}else{
				ArrayList<TrxnMessage> messageObjs = msgObj.getMultyObj(name);
				for(TrxnMessage messageObj : messageObjs){
					message.append(_pack(messageObj,protocolField.getFields(),trxnField.getFields()));

				}
			}
			
		}
		

		logger.info("change the byte array to string object...");

		logger.info("finish the package process....");

		return message.toString();
	}

	
	public int _unpack(String subMsg, ArrayList<Field> protocolFields,
			HashMap<String,com.xuchunchun.trxn.msg.cfg.Field> trxnFields,TrxnMessage trxnMessage) throws Exception {
		logger.info("start to unpackage the message....");

		int index = 0;
		
		for (int i = 0; i < protocolFields.size(); i++) {
			Field protocolField = protocolFields.get(i);
			String name = protocolField.getName();
			com.xuchunchun.trxn.msg.cfg.Field trxnField = trxnFields.get(name);
			logger.info("process the " + (i+1) + "th field, field name is " + name);
			
			
			
			if(!trxnField.isMulty()){
				String _subMsg = null;
				int contentLength = -1;
				
				switch(EnumType.valueOf(Field.TYPE.class, protocolField.getType())){
					case fix:{
						contentLength = protocolField.getLength();	
						break;
					}
					case dyn:{
						int headLength = protocolField.getHeadLength();
						
						contentLength = Integer.parseInt(subMsg.substring(index, headLength+index));
						
						index = headLength+index;
						
						break;
					}
				}
				
				if(contentLength == -1)throw new Exception("the field type in the protocol is error......");

				
				_subMsg = subMsg.substring(index, contentLength+index);
				
				index = contentLength+index;
				
				boolean valid = TrxnMessageUtil.checkRspMsg(trxnField.getCheckExpr(), _subMsg, trxnField.isNull());
				
				logger.info("check the message, the result is "+valid);
				
				if(!valid)throw new Exception("the fotmat of the field value is error，the value is :"+_subMsg+" the rull is:"+trxnField.getCheckExpr());
				
				trxnMessage.setSingleObj(name, TrxnMessageUtil.changeStrToObj(trxnField.getType(), trxnField.getDateFormat(), _subMsg));

			}else{
				String countFieldName = trxnField.getCountField();
				
				if(countFieldName == null || "".equalsIgnoreCase(countFieldName))throw new Exception("there is not count field value in the config file...");
				
				int count = 0;
				try{
					count = Integer.parseInt(trxnMessage.getSingleObj(countFieldName).toString().trim());
				}catch(Exception e){
					throw new Exception("the message is error, the cause is: "+e.getMessage());
				}
				
				ArrayList<TrxnMessage> subMessages = trxnMessage.getMultyObj(name);
				
				TrxnMessage copySubMessage = subMessages.get(0).clone();
				
				subMessages.remove(0);
				
				for(int j=0;j<count;j++){
					TrxnMessage subMessage = copySubMessage.clone();
					index = index + _unpack(subMsg.substring(index),protocolField.getFields(),trxnField.getFields(),subMessage);
					subMessages.add(subMessage);
				}
				trxnMessage.setMultyObj(name, subMessages);
			}
		}
		return index;
	}
	
	@Override
	public TrxnMessage unpack(byte[] msg, ProtocolConfig protocolConfig,
			String encoding, TrxnConfig trxnConfig) throws Exception {
		logger.info("start to unpackage the message....");

		String message = new String(msg,encoding);
		
		String name = trxnConfig.getTrxnName();
		
		TrxnMessage trxnMessage = TrxnMsgFactory.getInstance().getRspMessageClone(name);
		
		ArrayList<Field> protocolFields = protocolConfig.getResponseFields();

		if (protocolFields == null)
			throw new Exception("please load the protocol config files .....");
		
		HashMap<String,com.xuchunchun.trxn.msg.cfg.Field> trxnFields = trxnConfig.getResponseFields().getFields();

		if (trxnFields == null)
			throw new Exception("please load the trxn config files .....");
		
		_unpack(message,protocolFields,trxnFields,trxnMessage);
		
		logger.info("finish the unpackaging process");
		return trxnMessage;
	} 
	
	private String formatField(Field protocolField,com.xuchunchun.trxn.msg.cfg.Field trxnField,
			Object fieldObj) throws Exception{
		logger.info("format the field with name "+trxnField.getName());
		
		String fieldStr;
		if(fieldObj == null){
			if(!trxnField.isNull()) throw new Exception("field:"+trxnField.getName()+"can not be null...");
			else{
				fieldStr = "";
			}
		}else{
			fieldStr = TrxnMessageUtil.changeObjToStr(fieldObj, trxnField.getDateFormat());
		}
		
		if(fieldStr == null)throw new Exception("error occur changing the object to string....");
		
		fieldStr = formatFieldStr(protocolField,fieldStr);
		
		if(fieldStr == null)throw new Exception("error occur formating the field string....");
		
		return fieldStr;
	}
	
	private String formatFieldStr(Field protocolField,String fieldStr){
		switch(EnumType.valueOf(Field.TYPE.class, protocolField.getType())){
			case fix:{
				int contentLength = protocolField.getLength();
				char fillChar = protocolField.getContentFillChar();
				String fillType = protocolField.getContentFillType();
				return StrUtil.fill(fillType, fillChar, fieldStr, contentLength);
			}
			case dyn:{
				int headLength = protocolField.getHeadLength();
				char fillChar = protocolField.getHeadFillChar();
				String fillType = protocolField.getHeadFillType();
				
				int contentLength = fieldStr.length();
				
				return StrUtil.fill(fillType, fillChar, String.valueOf(contentLength), headLength)+fieldStr;
			}
			default: return null;
		}
	}

}
