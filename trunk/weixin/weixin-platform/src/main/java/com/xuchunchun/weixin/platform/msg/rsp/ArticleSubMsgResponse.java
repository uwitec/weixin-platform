package com.xuchunchun.weixin.platform.msg.rsp;

import com.xuchunchun.base.annotation.ResponseMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class ArticleSubMsgResponse {
	@ResponseMsgField(nodeName="Title",getValMethod="getTitle",isChanged=true)
	private String title;
	
	@ResponseMsgField(nodeName="Description",getValMethod="getDescription",isChanged=true)
	private String description;
	
	@ResponseMsgField(nodeName="PicUrl",getValMethod="getPicUrl",isChanged=true)
	private String picUrl;
	
	@ResponseMsgField(nodeName="Url",getValMethod="getUrl",isChanged=true)
	private String url;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
