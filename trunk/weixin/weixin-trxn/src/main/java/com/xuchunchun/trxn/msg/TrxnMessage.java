package com.xuchunchun.trxn.msg;

import java.util.ArrayList;
import java.util.HashMap;

public class TrxnMessage {
	private HashMap<String,Object> message = new HashMap<String,Object>();
	
	public Object getSingleObj(String key){
		return message.get(key);
	}
	
	public boolean setSingleObj(String key,Object obj){
		if(!message.containsKey(key))return false;
		message.put(key, obj);
		return true;
	}
	
	public void setSingleObj(String key){
		message.put(key, null);
	}
	
	public ArrayList<TrxnMessage> getMultyObj(String key){
		return (ArrayList<TrxnMessage>)message.get(key);
	}
	
	public boolean setMultyObj(String key,TrxnMessage subMsg){
		if(!message.containsKey(key)){
			message.put(key, new ArrayList<TrxnMessage>());
		}
		((ArrayList<TrxnMessage>)message.get(key)).add(subMsg);
		return true;
	}
	
	public boolean setMultyObj(String key,ArrayList<TrxnMessage> subMsgs){
		if(!message.containsKey(key))return false;
		message.put(key, subMsgs);
		return true;
	}
	
	public void setMultyObj(String key){
		message.put(key, null);
	}
	
	public void setMessage(HashMap<String,Object> message){
		this.message = message;
	}
	
	public HashMap<String,Object> getMessage(){
		return this.message;
	}
	
	public TrxnMessage clone(){
		TrxnMessage trxnMessage = new TrxnMessage();
		trxnMessage.setMessage((HashMap<String,Object>)this.message.clone());
		return trxnMessage;
	}

}
