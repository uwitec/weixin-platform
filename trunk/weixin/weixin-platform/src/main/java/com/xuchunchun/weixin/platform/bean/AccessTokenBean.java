package com.xuchunchun.weixin.platform.bean;

import com.xuchunchun.base.util.JsonUtil;

public class AccessTokenBean {
	private String access_token;
	private int expires_in;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	
	public static void main(String args[]){
		
		String s = "{\"access_token\":\"ff_TOKEN\",\"expires_in\":7200}";
		
		AccessTokenBean a = JsonUtil.parseToBean(s, AccessTokenBean.class);
		
		System.out.println(a.getAccess_token());
		System.out.println(a.getExpires_in());
		
	}
}
