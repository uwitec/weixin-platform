package com.xuchunchun.weixin.platform.msg.req;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class TextMsgRequest extends PushMsg {
	@RequestMsgField(nodeName="Content",addValMethod="setContent",parameter=String.class,isChanged=true)
	private String content;
	
	@RequestMsgField(nodeName="MsgId",addValMethod="setMsgId",parameter=long.class)
	private long msgId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
}
