package com.xuchunchun.weixin.platform.bean;

public class SingleMsgSendBean {
	private String type = "1";
    private String content = "";
    private String error = "false";
    private String token = "";
    private String ajax = "1";
    private String tofakeid = "";
    
    public enum MsgType {
        TEXT(1), VOICE(3), IMAGE(2), VIDEO(4), IMAGE_TEXT(10);
        private int type;

        private MsgType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

    }
    
	public String getType() {
		return type;
	}
	public void setType(MsgType type) {
		this.type = String.valueOf(type.getType());
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAjax() {
		return ajax;
	}
	public void setAjax(String ajax) {
		this.ajax = ajax;
	}
	public String getTofakeid() {
		return tofakeid;
	}
	public void setTofakeid(String tofakeid) {
		this.tofakeid = tofakeid;
	}
}
