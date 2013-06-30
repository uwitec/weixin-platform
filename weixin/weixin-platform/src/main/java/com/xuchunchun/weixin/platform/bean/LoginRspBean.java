package com.xuchunchun.weixin.platform.bean;

import com.xuchunchun.base.util.JsonUtil;

public class LoginRspBean {
	public int Ret;
    public String ErrMsg;
    public int ShowVerifyCode;
    public int ErrCode;
	public int getRet() {
		return Ret;
	}
	public void setRet(int ret) {
		Ret = ret;
	}
	public String getErrMsg() {
		return ErrMsg;
	}
	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}
	public int getShowVerifyCode() {
		return ShowVerifyCode;
	}
	public void setShowVerifyCode(int showVerifyCode) {
		ShowVerifyCode = showVerifyCode;
	}
	public int getErrCode() {
		return ErrCode;
	}
	public void setErrCode(int errCode) {
		ErrCode = errCode;
	}
	
	public static void main(String args[]){
		String s = "{\n\"Ret\": 302,\n\"ErrMsg\": \"/cgi-bin/indexpage?t=wxm-index&lang=zh_CN&token=1331604278\",\n\"ShowVerifyCode\": 0,\n\"ErrCode\": 0\n}";
		//String s = "{\"Ret\": 302,\"ErrMsg\": \"/cgi-bin/indexpage?t=wxm-index&lang=zh_CN&token=1331604278\",\"ShowVerifyCode\": 0,\"ErrCode\": 0}";

		LoginRspBean b = JsonUtil.parseToBean(s, LoginRspBean.class);
		System.out.println(b.getRet());
	}
}
