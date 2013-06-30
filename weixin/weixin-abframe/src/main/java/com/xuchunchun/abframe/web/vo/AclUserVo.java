package com.xuchunchun.abframe.web.vo;

import java.util.Date;

public class AclUserVo {
	private String userId;
	private String employeeId;
	private String password;
	private String authMode;
	private String userStatus;
	private Date unlocktime;
	private Date lastlogin;
	private Long errCount;
	private String loginStrategy;
	private Long bankorgId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthMode() {
		return authMode;
	}
	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public Date getUnlocktime() {
		return unlocktime;
	}
	public void setUnlocktime(Date unlocktime) {
		this.unlocktime = unlocktime;
	}
	public Date getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	public Long getErrCount() {
		return errCount;
	}
	public void setErrCount(Long errCount) {
		this.errCount = errCount;
	}
	public String getLoginStrategy() {
		return loginStrategy;
	}
	public void setLoginStrategy(String loginStrategy) {
		this.loginStrategy = loginStrategy;
	}
	public Long getBankorgId() {
		return bankorgId;
	}
	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}
	public AclUserVo(String userId, String employeeId, String password,
			String authMode, String userStatus, Date unlocktime,
			Date lastlogin, Long errCount, String loginStrategy, Long bankorgId) {
		super();
		this.userId = userId;
		this.employeeId = employeeId;
		this.password = password;
		this.authMode = authMode;
		this.userStatus = userStatus;
		this.unlocktime = unlocktime;
		this.lastlogin = lastlogin;
		this.errCount = errCount;
		this.loginStrategy = loginStrategy;
		this.bankorgId = bankorgId;
	}
	public AclUserVo() {
		super();
	}
	
}
