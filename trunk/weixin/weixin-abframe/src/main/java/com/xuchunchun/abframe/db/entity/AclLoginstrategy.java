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
 * AclLoginstrategy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_LOGINSTRATEGY")
public class AclLoginstrategy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclLoginstrategyId id;
	private String strategyName;
	private String macCheck;
	private String ipCheck;
	private String listCheck;
	private String listType;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclLoginstrategy() {
	}

	/** minimal constructor */
	public AclLoginstrategy(AclLoginstrategyId id) {
		this.id = id;
	}

	/** full constructor */
	public AclLoginstrategy(AclLoginstrategyId id, String strategyName,
			String macCheck, String ipCheck, String listCheck, String listType,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.strategyName = strategyName;
		this.macCheck = macCheck;
		this.ipCheck = ipCheck;
		this.listCheck = listCheck;
		this.listType = listType;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "loginStrategy", column = @Column(name = "LOGIN_STRATEGY", nullable = false, length = 10)) })
	public AclLoginstrategyId getId() {
		return this.id;
	}

	public void setId(AclLoginstrategyId id) {
		this.id = id;
	}

	@Column(name = "STRATEGY_NAME", length = 40)
	public String getStrategyName() {
		return this.strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	@Column(name = "MAC_CHECK", length = 1)
	public String getMacCheck() {
		return this.macCheck;
	}

	public void setMacCheck(String macCheck) {
		this.macCheck = macCheck;
	}

	@Column(name = "IP_CHECK", length = 1)
	public String getIpCheck() {
		return this.ipCheck;
	}

	public void setIpCheck(String ipCheck) {
		this.ipCheck = ipCheck;
	}

	@Column(name = "LIST_CHECK", length = 1)
	public String getListCheck() {
		return this.listCheck;
	}

	public void setListCheck(String listCheck) {
		this.listCheck = listCheck;
	}

	@Column(name = "LIST_TYPE", length = 1)
	public String getListType() {
		return this.listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
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