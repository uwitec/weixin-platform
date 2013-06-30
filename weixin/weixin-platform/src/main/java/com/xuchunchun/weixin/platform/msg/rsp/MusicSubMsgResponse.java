package com.xuchunchun.weixin.platform.msg.rsp;

import com.xuchunchun.base.annotation.ResponseMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class MusicSubMsgResponse {
	@ResponseMsgField(nodeName="Title",getValMethod="getTitle",isChanged=true)
	private String title;
	
	@ResponseMsgField(nodeName="Description",getValMethod="getDescription",isChanged=true)
	private String description;
	
	@ResponseMsgField(nodeName="MusicUrl",getValMethod="getMusicUrl",isChanged=true)
	private String musicUrl;
	
	@ResponseMsgField(nodeName="HQMusicUrl",getValMethod="getHqMusicUrl",isChanged=true)
	private String hqMusicUrl;

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	

	
}
