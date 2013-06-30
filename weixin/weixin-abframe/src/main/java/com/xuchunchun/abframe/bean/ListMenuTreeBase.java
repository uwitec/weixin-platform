package com.xuchunchun.abframe.bean;

import java.util.Comparator;

public class ListMenuTreeBase implements Comparator<ListMenuTreeBase>{
	private String text ; //页面显示名字，节点名称
	private String nodeId;//节点ID
	private Long nodeSeq;//节点序号
	private String url;
	private boolean ischecked = false; //默认是否选中 
	@Override
	public int compare(ListMenuTreeBase o1, ListMenuTreeBase o2) {
		return (int)(o1.getNodeSeq()-((ListMenuTreeBase) o2).getNodeSeq());
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public Long getNodeSeq() {
		return nodeSeq;
	}
	public void setNodeSeq(Long nodeSeq) {
		this.nodeSeq = nodeSeq;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

	public boolean isIschecked() {
		return ischecked;
	}
}
