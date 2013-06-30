package com.xuchunchun.weixin.platform.bean;

public class TextSendBean {
	private String touser;
	private String msgtype = "text";
	private TextBean text;
	
	public void setContent(String content){
		TextBean textBean = new TextBean();
		textBean.setContent(content);
		this.setText(textBean);
	}
	
	public class TextBean{
		private String content;
		
		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public TextBean getText() {
		return text;
	}

	public void setText(TextBean text) {
		this.text = text;
	}
}
