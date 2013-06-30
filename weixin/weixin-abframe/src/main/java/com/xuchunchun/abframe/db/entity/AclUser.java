package com.xuchunchun.abframe.db.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AclUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_USER")
public class AclUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
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
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclUser() {
	}

	/** minimal constructor */
	public AclUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public AclUser(String userId, String employeeId, String password,
			String authMode, String userStatus, Date unlocktime,
			Date lastlogin, Long errCount, String loginStrategy,
			Long bankorgId, Date modiDate, String modiUser, Integer version) {
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
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "EMPLOYEE_ID", length = 10)
	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "PASSWORD", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "AUTH_MODE", length = 1)
	public String getAuthMode() {
		return this.authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	@Column(name = "USER_STATUS", length = 1)
	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UNLOCKTIME", length = 7)
	public Date getUnlocktime() {
		return this.unlocktime;
	}

	public void setUnlocktime(Date unlocktime) {
		this.unlocktime = unlocktime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LASTLOGIN", length = 7)
	public Date getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	@Column(name = "ERR_COUNT", precision = 10, scale = 0)
	public Long getErrCount() {
		return this.errCount;
	}

	public void setErrCount(Long errCount) {
		this.errCount = errCount;
	}

	@Column(name = "LOGIN_STRATEGY", length = 10)
	public String getLoginStrategy() {
		return this.loginStrategy;
	}

	public void setLoginStrategy(String loginStrategy) {
		this.loginStrategy = loginStrategy;
	}

	@Column(name = "BANKORG_ID", precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODI_DATE", length = 7)
	public Date getModiDate() {
		return this.modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	@Column(name = "MODI_USER", length = 64)
	public String getModiUser() {
		return this.modiUser;
	}

	public void setModiUser(String modiUser) {
		this.modiUser = modiUser;
	}

	@Column(name = "VERSION", precision = 8, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}