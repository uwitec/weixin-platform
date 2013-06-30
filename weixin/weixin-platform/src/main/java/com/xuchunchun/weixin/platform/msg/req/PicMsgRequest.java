package com.xuchunchun.weixin.platform.msg.req;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class PicMsgRequest extends PushMsg {
	@RequestMsgField(nodeName="PicUrl",addValMethod="setPicUrl",parameter=String.class,isChanged=true)
	private String picUrl;
	
	@RequestMsgField(nodeName="MsgId",addValMethod="setMsgId",parameter=long.class)
	private long msgId;


	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
