package com.xuchunchun.weixin.platform.msg.rsp;

import com.xuchunchun.base.annotation.ResponseMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class TextMsgResponse extends PushMsg {
	@ResponseMsgField(nodeName="Content",getValMethod="getContent",isChanged=true)
	private String content;
	
	@ResponseMsgField(nodeName="FuncFlag",getValMethod="getFuncFlag")
	private String funcFlag;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	
}
