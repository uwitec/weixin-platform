package com.xuchunchun.weixin.platform.bean;

import java.util.List;

public class ModifyContactRspBean {
	private String ret;
	private String fakeId;
	private List<ModifyContactRspBean> result;
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getFakeId() {
		return fakeId;
	}
	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}
	public List<ModifyContactRspBean> getResult() {
		return result;
	}
	public void setResult(List<ModifyContactRspBean> result) {
		this.result = result;
	}
}
