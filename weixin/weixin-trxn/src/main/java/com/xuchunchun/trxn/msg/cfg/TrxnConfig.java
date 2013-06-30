package com.xuchunchun.trxn.msg.cfg;

public class TrxnConfig {
	private String trxnName;
	private String trxnDesc;
	private RequestFields requestFields;
	private ResponseFields responseFields;
	public String getTrxnName() {
		return trxnName;
	}
	public void setTrxnName(String trxnName) {
		this.trxnName = trxnName;
	}
	public String getTrxnDesc() {
		return trxnDesc;
	}
	public void setTrxnDesc(String trxnDesc) {
		this.trxnDesc = trxnDesc;
	}
	public RequestFields getRequestFields() {
		return requestFields;
	}
	public void setRequestFields(RequestFields requestFields) {
		this.requestFields = requestFields;
	}
	public ResponseFields getResponseFields() {
		return responseFields;
	}
	public void setResponseFields(ResponseFields responseFields) {
		this.responseFields = responseFields;
	}
}
