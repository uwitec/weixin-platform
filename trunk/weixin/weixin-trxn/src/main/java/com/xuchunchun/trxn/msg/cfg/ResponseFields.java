package com.xuchunchun.trxn.msg.cfg;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseFields {
	private HashMap<String,Field> fields = null;
	private HashMap<String,KeyField> keyFields = new HashMap<String,KeyField>();
	private String reasonField = null;
	
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
	
	public HashMap<String,KeyField> getKeyFields(){
		return keyFields;
	}
	
	public void addKeyField(KeyField keyField){
		keyFields.put(keyField.getName(),keyField);
	}

	public String getReasonField() {
		return reasonField;
	}

	public void setReasonField(String reasonField) {
		this.reasonField = reasonField;
	}
}
