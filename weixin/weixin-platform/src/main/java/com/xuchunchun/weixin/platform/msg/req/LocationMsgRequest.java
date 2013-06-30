package com.xuchunchun.weixin.platform.msg.req;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class LocationMsgRequest extends PushMsg {
	@RequestMsgField(nodeName="Location_X",addValMethod="setLocation_X",parameter=String.class)
	private String location_X;
	
	@RequestMsgField(nodeName="Location_Y",addValMethod="setLocation_Y",parameter=String.class)
	private String location_Y;
	
	@RequestMsgField(nodeName="Scale",addValMethod="setScale",parameter=int.class)
	private int scale;
	
	@RequestMsgField(nodeName="Label",addValMethod="setLabel",parameter=String.class,isChanged=true)
	private String label;
	
	@RequestMsgField(nodeName="MsgId",addValMethod="setMsgId",parameter=long.class)
	private long msgId;


	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
}
