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
 * WxTrxnmapping entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_TRXNMAPPING")
public class WxTrxnmapping implements java.io.Serializable {

	// Fields

	private WxTrxnmappingId id;
	private String trxnId;
	private String rspMsgType;
	private String clientName;
	private Date modiDate;
	private String modiUser;
	private Integer version;
	private String msgTempId;

	// Constructors

	/** default constructor */
	public WxTrxnmapping() {
	}

	/** minimal constructor */
	public WxTrxnmapping(WxTrxnmappingId id) {
		this.id = id;
	}

	/** full constructor */
	public WxTrxnmapping(WxTrxnmappingId id, String trxnId, String rspMsgType,
			String clientName, Date modiDate, String modiUser, Integer version,
			String msgTempId) {
		this.id = id;
		this.trxnId = trxnId;
		this.rspMsgType = rspMsgType;
		this.clientName = clientName;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
		this.msgTempId = msgTempId;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "msgType", column = @Column(name = "MSG_TYPE", nullable = false, length = 10)),
			@AttributeOverride(name = "msgKey", column = @Column(name = "MSG_KEY", nullable = false, length = 100)) })
	public WxTrxnmappingId getId() {
		return this.id;
	}

	public void setId(WxTrxnmappingId id) {
		this.id = id;
	}

	@Column(name = "TRXN_ID", length = 10)
	public String getTrxnId() {
		return this.trxnId;
	}

	public void setTrxnId(String trxnId) {
		this.trxnId = trxnId;
	}

	@Column(name = "RSP_MSG_TYPE", length = 10)
	public String getRspMsgType() {
		return this.rspMsgType;
	}

	public void setRspMsgType(String rspMsgType) {
		this.rspMsgType = rspMsgType;
	}

	@Column(name = "CLIENT_NAME", length = 10)
	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	@Column(name = "MSG_TEMP_ID", length = 10)
	public String getMsgTempId() {
		return this.msgTempId;
	}

	public void setMsgTempId(String msgTempId) {
		this.msgTempId = msgTempId;
	}

}