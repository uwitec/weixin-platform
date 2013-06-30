package com.xuchunchun.weixin.platform.msg;

import java.util.Date;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.base.annotation.ResponseMsgField;

public class PushMsg {
	
	public static enum requestMsgType{
		text,image,location,link,event
	}
	public static enum responseMsgType{
		text,music,news
	}
	
	
	@RequestMsgField(nodeName="ToUserName",addValMethod="setToUserName",parameter=String.class,isChanged=true)
	@ResponseMsgField(nodeName="ToUserName",getValMethod="getToUserName",isChanged=true)
	private String toUserName;
	
	@RequestMsgField(nodeName="FromUserName",addValMethod="setFromUserName",parameter=String.class,isChanged=true)
	@ResponseMsgField(nodeName="FromUserName",getValMethod="getFromUserName",isChanged=true)
	private String fromUserName;
	
	@RequestMsgField(nodeName="CreateTime",addValMethod="setCreateTime",parameter=long.class)
	@ResponseMsgField(nodeName="CreateTime",getValMethod="getCreateTime")
	private Date createTime ;
	
	@RequestMsgField(nodeName="MsgType",addValMethod="setMsgType",parameter=String.class,isChanged=true)
	@ResponseMsgField(nodeName="MsgType",getValMethod="getMsgType",isChanged=true)
	private String msgType ;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = new Date(createTime);
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
