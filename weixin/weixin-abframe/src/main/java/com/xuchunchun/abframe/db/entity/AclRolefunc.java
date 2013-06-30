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
 * AclRolefunc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_ROLEFUNC")
public class AclRolefunc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclRolefuncId id;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclRolefunc() {
	}

	/** minimal constructor */
	public AclRolefunc(AclRolefuncId id) {
		this.id = id;
	}

	/** full constructor */
	public AclRolefunc(AclRolefuncId id, Date modiDate, String modiUser,
			Integer version) {
		this.id = id;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "roldId", column = @Column(name = "ROLD_ID", nullable = false, length = 20)),
			@AttributeOverride(name = "functionId", column = @Column(name = "FUNCTION_ID", nullable = false, length = 20)) })
	public AclRolefuncId getId() {
		return this.id;
	}

	public void setId(AclRolefuncId id) {
		this.id = id;
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