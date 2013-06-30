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
 * AclPassword entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_PASSWORD")
public class AclPassword implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclPasswordId id;
	private String password;
	private Date startDate;
	private Date endDate;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclPassword() {
	}

	/** minimal constructor */
	public AclPassword(AclPasswordId id) {
		this.id = id;
	}

	/** full constructor */
	public AclPassword(AclPasswordId id, String password, Date startDate,
			Date endDate, Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.password = password;
		this.startDate = startDate;
		this.endDate = endDate;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false, length = 10)),
			@AttributeOverride(name = "pwdSeq", column = @Column(name = "PWD_SEQ", nullable = false, precision = 10, scale = 0)) })
	public AclPasswordId getId() {
		return this.id;
	}

	public void setId(AclPasswordId id) {
		this.id = id;
	}

	@Column(name = "PASSWORD", length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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