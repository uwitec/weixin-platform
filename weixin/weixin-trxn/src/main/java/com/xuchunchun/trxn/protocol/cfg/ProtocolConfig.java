package com.xuchunchun.trxn.protocol.cfg;

import java.util.ArrayList;
import java.util.HashMap;

public class ProtocolConfig {
	private String trxnName;
	private String proType;
	public static enum PROTOCOL{
		fix,xml
	}
	
	private ArrayList<Field> requestFields = null;
	private ArrayList<Field> responseFields = null;

	
	public void setRequestFields(ArrayList<Field> requestFields){
		this.requestFields = requestFields;
	}
	
	public ArrayList<Field> getRequestFields(){
		return this.requestFields;
	}
	
	public String getTrxnName() {
		return trxnName;
	}
	public void setTrxnName(String trxnName) {
		this.trxnName = trxnName;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}

	public ArrayList<Field> getResponseFields() {
		return responseFields;
	}

	public void setResponseFields(ArrayList<Field> responseFields) {
		this.responseFields = responseFields;
	}
}
