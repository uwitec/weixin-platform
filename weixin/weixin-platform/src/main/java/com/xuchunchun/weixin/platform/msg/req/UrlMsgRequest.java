package com.xuchunchun.weixin.platform.msg.req;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class UrlMsgRequest extends PushMsg {
	@RequestMsgField(nodeName="Title",addValMethod="setTitle",parameter=String.class,isChanged=true)
	private String title;
	
	@RequestMsgField(nodeName="Description",addValMethod="setDescription",parameter=String.class,isChanged=true)
	private String description;
	
	@RequestMsgField(nodeName="Url",addValMethod="setUrl",parameter=String.class,isChanged=true)
	private String url;
	
	@RequestMsgField(nodeName="MsgId",addValMethod="setMsgId",parameter=long.class)
	private long msgId;


	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
