package com.xuchunchun.trxn.msg.cfg;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {

	private String name;
	
	private String desc;
		
	private boolean multy;
	
	private String type;
	
	private String dateFormat;
	
	private String checkExpr;
	
	private boolean isNull;
	
	private String countField;
	
	private String fillValue;
	
	private String memoFiled;
	
	private HashMap<String,Field> fields = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isMulty() {
		return multy;
	}

	public void setMulty(boolean multy) {
		this.multy = multy;
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashMap<String,Field> getFields() {
		return fields;
	}
	
	public Field getFiled(String name){
		return fields.get(name);
	}

	public void setFields(HashMap<String,Field> fields) {
		this.fields = fields;
	}
	
	public void setFields(Field _field){
		if(fields == null)fields = new HashMap<String,Field>();

		this.fields.put(_field.getName(),_field);
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getCheckExpr() {
		return checkExpr;
	}

	public void setCheckExpr(String checkExpr) {
		this.checkExpr = checkExpr;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getCountField() {
		return countField;
	}

	public void setCountField(String countField) {
		this.countField = countField;
	}

	public String getFillValue() {
		return fillValue;
	}

	public void setFillValue(String fillValue) {
		this.fillValue = fillValue;
	}

	public String getMemoFiled() {
		return memoFiled;
	}

	public void setMemoFiled(String memoFiled) {
		this.memoFiled = memoFiled;
	}
}
