package com.xuchunchun.abframe.web.vo;

import com.xuchunchun.abframe.db.entity.AclLoginstrategyId;


/**
 * 登录策略VO
 * @author wangyang
 *
 */
public class AclLoginstrategyVo {
	private AclLoginstrategyId id;
	private String strategyName;
	private String macCheck;
	private String ipCheck;
	private String listCheck;
	private String listType;
	public AclLoginstrategyId getId() {
		return id;
	}
	public void setId(AclLoginstrategyId id) {
		this.id = id;
	}
	public String getStrategyName() {
		return strategyName;
	}
	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}
	public String getMacCheck() {
		return macCheck;
	}
	public void setMacCheck(String macCheck) {
		this.macCheck = macCheck;
	}
	public String getIpCheck() {
		return ipCheck;
	}
	public void setIpCheck(String ipCheck) {
		this.ipCheck = ipCheck;
	}
	public String getListCheck() {
		return listCheck;
	}
	public void setListCheck(String listCheck) {
		this.listCheck = listCheck;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public AclLoginstrategyVo(
			AclLoginstrategyId id,
			String strategyName, String macCheck, String ipCheck,
			String listCheck, String listType) {
		super();
		this.id = id;
		this.strategyName = strategyName;
		this.macCheck = macCheck;
		this.ipCheck = ipCheck;
		this.listCheck = listCheck;
		this.listType = listType;
	}
	public AclLoginstrategyVo() {
		super();
	}
	
}
