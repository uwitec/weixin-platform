package com.xuchunchun.weixin.platform.bean;

import java.util.List;

public class UserListBean {
	private int total;
	private int count;
	private String next_openid;
	private ListInfoBean data;
	
	public class ListInfoBean{
		private List<String> openid;
		public List<String> getOpenid() {
			return openid;
		}

		public void setOpenid(List<String> openid) {
			this.openid = openid;
		} 
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

	public ListInfoBean getData() {
		return data;
	}

	public void setData(ListInfoBean data) {
		this.data = data;
	}

	
}
