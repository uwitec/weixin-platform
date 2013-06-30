package com.xuchunchun.abframe.bean;

public class AclTopMenuTreeForm {
	private String title;
	private String id;
	private String childrenString; //list转换成json字符串
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setChildrenString(String childrenString) {
		this.childrenString = childrenString;
	}
	public String getChildrenString() {
		return childrenString;
	}
}
