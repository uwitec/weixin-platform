package com.xuchunchun.weixin.platform.msg.rsp;

import com.xuchunchun.base.annotation.ResponseMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class MusicMsgResponse extends PushMsg {
	@ResponseMsgField(nodeName="Music",getValMethod="getMusic",isComp=true)
	private MusicSubMsgResponse music;
	
	@ResponseMsgField(nodeName="FuncFlag",getValMethod="getFuncFlag")
	private String funcFlag;

	public MusicSubMsgResponse getMusic() {
		return music;
	}

	public void setMusic(MusicSubMsgResponse music) {
		this.music = music;
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	
	
}
