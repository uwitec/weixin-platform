package com.xuchunchun.weixin.platform.bean;

import java.util.ArrayList;
import java.util.List;

public class ModifyContactBean {
	private int groupId;
	private List<String> fakeList = new ArrayList<String>();
    private String token = "";
    private static final String seperate = "|";
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public List<String> getFakeList() {
		return fakeList;
	}
	public String getFackListStr(){
		StringBuffer sb = new StringBuffer();
		for(String fakeId : fakeList){
			sb.append(fakeId);
			sb.append(seperate);
		}
		return sb.toString().substring(0, sb.length()-1);
	}
	public void setFakeList(List<String> fakeList) {
		this.fakeList = fakeList;
	}
	public void addFakeId(String fakeId){
		this.fakeList.add(fakeId);
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
