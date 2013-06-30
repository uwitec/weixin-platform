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
 * AclUserlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_USERLIST")
public class AclUserlist implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclUserlistId id;
	private Date startDate;
	private Date endDate;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclUserlist() {
	}

	/** minimal constructor */
	public AclUserlist(AclUserlistId id) {
		this.id = id;
	}

	/** full constructor */
	public AclUserlist(AclUserlistId id, Date startDate, Date endDate,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
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
			@AttributeOverride(name = "listType", column = @Column(name = "LIST_TYPE", nullable = false, length = 1)) })
	public AclUserlistId getId() {
		return this.id;
	}

	public void setId(AclUserlistId id) {
		this.id = id;
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