package com.xuchunchun.weixin.platform.msg.req;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class EventMsgRequest extends PushMsg {
	public static enum EVENT{
		subscribe,unsubscribe,CLICK
	}; 
	
	@RequestMsgField(nodeName="Event",addValMethod="setEvent",parameter=String.class,isChanged=true)
	private String event;
	
	@RequestMsgField(nodeName="EventKey",addValMethod="setEventKey",parameter=String.class,isChanged=true)
	private String eventKey;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}


	
}
