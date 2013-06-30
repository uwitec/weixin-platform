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
 * AclRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_ROLE")
public class AclRole implements java.io.Serializable {

	// Fields

	private AclRoleId id;
	private String roldName;
	private String roleType;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclRole() {
	}

	/** minimal constructor */
	public AclRole(AclRoleId id) {
		this.id = id;
	}

	/** full constructor */
	public AclRole(AclRoleId id, String roldName, String roleType,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.roldName = roldName;
		this.roleType = roleType;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "roldId", column = @Column(name = "ROLD_ID", nullable = false, length = 20)) })
	public AclRoleId getId() {
		return this.id;
	}

	public void setId(AclRoleId id) {
		this.id = id;
	}

	@Column(name = "ROLD_NAME", length = 40)
	public String getRoldName() {
		return this.roldName;
	}

	public void setRoldName(String roldName) {
		this.roldName = roldName;
	}

	@Column(name = "ROLE_TYPE", length = 1)
	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODI_DATE", length = 10)
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