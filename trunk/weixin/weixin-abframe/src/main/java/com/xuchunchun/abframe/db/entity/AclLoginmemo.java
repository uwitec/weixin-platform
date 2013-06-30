package com.xuchunchun.abframe.db.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AclLoginmemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_LOGINMEMO")
public class AclLoginmemo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclLoginmemoId id;
	private Date loginDate;
	private String authMode;
	private String userStatus;
	private Long errCount;
	private String loginStrategy;
	private String loginResult;
	private String reasonType;
	private String loginMemo;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclLoginmemo() {
	}

	/** minimal constructor */
	public AclLoginmemo(AclLoginmemoId id) {
		this.id = id;
	}

	/** full constructor */
	public AclLoginmemo(AclLoginmemoId id, Date loginDate, String authMode,
			String userStatus, Long errCount, String loginStrategy,
			String loginResult, String reasonType, String loginMemo,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.loginDate = loginDate;
		this.authMode = authMode;
		this.userStatus = userStatus;
		this.errCount = errCount;
		this.loginStrategy = loginStrategy;
		this.loginResult = loginResult;
		this.reasonType = reasonType;
		this.loginMemo = loginMemo;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 10)),
			@AttributeOverride(name = "loginSeq", column = @Column(name = "LOGIN_SEQ", nullable = false, precision = 10, scale = 0)) })
	public AclLoginmemoId getId() {
		return this.id;
	}

	public void setId(AclLoginmemoId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOGIN_DATE", length = 7)
	public Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
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

	@Column(name = "LOGIN_RESULT", length = 1)
	public String getLoginResult() {
		return this.loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	@Column(name = "REASON_TYPE", length = 1)
	public String getReasonType() {
		return this.reasonType;
	}

	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}

	@Column(name = "LOGIN_MEMO", length = 200)
	public String getLoginMemo() {
		return this.loginMemo;
	}

	public void setLoginMemo(String loginMemo) {
		this.loginMemo = loginMemo;
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