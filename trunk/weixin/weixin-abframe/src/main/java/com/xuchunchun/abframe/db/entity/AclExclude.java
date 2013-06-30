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
 * AclExclude entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_EXCLUDE")
public class AclExclude implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclExcludeId id;
	private String excludeName;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclExclude() {
	}

	/** minimal constructor */
	public AclExclude(AclExcludeId id) {
		this.id = id;
	}

	/** full constructor */
	public AclExclude(AclExcludeId id, String excludeName, Date modiDate,
			String modiUser, Integer version) {
		this.id = id;
		this.excludeName = excludeName;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "excludeId", column = @Column(name = "EXCLUDE_ID", nullable = false, length = 40)),
			@AttributeOverride(name = "systemId", column = @Column(name = "SYSTEM_ID", nullable = false, length = 20)) })
	public AclExcludeId getId() {
		return this.id;
	}

	public void setId(AclExcludeId id) {
		this.id = id;
	}

	@Column(name = "EXCLUDE_NAME", length = 40)
	public String getExcludeName() {
		return this.excludeName;
	}

	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
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