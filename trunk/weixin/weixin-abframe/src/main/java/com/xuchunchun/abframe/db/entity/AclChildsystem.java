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
 * AclChildsystem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_CHILDSYSTEM")
public class AclChildsystem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclChildsystemId id;
	private String systemName;
	private String sysUrl;
	private Date modiDate;
	private String modiUser;
	private Integer version;
	private String logoutUrl;
	private String loginUrl;

	// Constructors

	/** default constructor */
	public AclChildsystem() {
	}

	/** minimal constructor */
	public AclChildsystem(AclChildsystemId id) {
		this.id = id;
	}

	/** full constructor */
	public AclChildsystem(AclChildsystemId id, String systemName,
			String sysUrl, Date modiDate, String modiUser, Integer version,
			String logoutUrl, String loginUrl) {
		this.id = id;
		this.systemName = systemName;
		this.sysUrl = sysUrl;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
		this.logoutUrl = logoutUrl;
		this.loginUrl = loginUrl;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "systemId", column = @Column(name = "SYSTEM_ID", nullable = false, length = 40)) })
	public AclChildsystemId getId() {
		return this.id;
	}

	public void setId(AclChildsystemId id) {
		this.id = id;
	}

	@Column(name = "SYSTEM_NAME", length = 40)
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	@Column(name = "SYS_URL", length = 200)
	public String getSysUrl() {
		return this.sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
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

	@Column(name = "LOGOUT_URL", length = 200)
	public String getLogoutUrl() {
		return this.logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	@Column(name = "LOGIN_URL", length = 200)
	public String getLoginUrl() {
		return this.loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}