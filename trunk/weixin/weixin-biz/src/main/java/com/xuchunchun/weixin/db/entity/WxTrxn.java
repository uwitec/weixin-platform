package com.xuchunchun.weixin.db.entity;

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
 * WxTrxn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_TRXN")
public class WxTrxn implements java.io.Serializable {

	// Fields

	private WxTrxnId id;
	private String trxnName;
	private String configName;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxTrxn() {
	}

	/** minimal constructor */
	public WxTrxn(WxTrxnId id) {
		this.id = id;
	}

	/** full constructor */
	public WxTrxn(WxTrxnId id, String trxnName, String configName,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.trxnName = trxnName;
		this.configName = configName;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "trxnId", column = @Column(name = "TRXN_ID", nullable = false, length = 10)) })
	public WxTrxnId getId() {
		return this.id;
	}

	public void setId(WxTrxnId id) {
		this.id = id;
	}

	@Column(name = "TRXN_NAME", length = 40)
	public String getTrxnName() {
		return this.trxnName;
	}

	public void setTrxnName(String trxnName) {
		this.trxnName = trxnName;
	}

	@Column(name = "CONFIG_NAME", length = 10)
	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
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