package com.xuchunchun.weixin.platform.bean;

import com.xuchunchun.base.util.JsonUtil;

public class ReponseMsgBean {
	private String errcode;
	
	private String errmsg;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public static void main(String args[]){
		
		String s = "{\"access_token\":\"ff_TOKEN\",\"expires_in\":7200}";
		
		ReponseMsgBean a = JsonUtil.parseToBean(s, ReponseMsgBean.class);
		
		System.out.println("["+(a.getErrcode()==null?"null":a.getErrcode())+"]");
		
	}
}
