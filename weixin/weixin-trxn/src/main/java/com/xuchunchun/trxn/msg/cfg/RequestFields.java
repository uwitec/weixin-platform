package com.xuchunchun.trxn.msg.cfg;

import java.util.ArrayList;
import java.util.HashMap;

public class RequestFields {
	private HashMap<String,Field> fields = null;
	private HashMap<String,Boolean> preloads = new HashMap<String,Boolean>();
	
	public void addPreloads(String preload,boolean isMulty){
		preloads.put(preload,isMulty);
	}
	
	public HashMap<String,Boolean> getPreloads(){
		return preloads;
	}
	
	public HashMap<String,Field> getFields(){
		return fields;
	}
	
	public void addField(Field field){
		if(fields == null)fields = new HashMap<String,Field>();
		fields.put(field.getName(),field);
	}
	
	public void setFields(HashMap<String,Field> fields){
		this.fields = fields;
	}
}
