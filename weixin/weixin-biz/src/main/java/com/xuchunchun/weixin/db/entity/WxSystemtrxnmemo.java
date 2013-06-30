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
 * WxSystemtrxnmemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_SYSTEMTRXNMEMO")
public class WxSystemtrxnmemo implements java.io.Serializable {

	// Fields

	private WxSystemtrxnmemoId id;
	private String wxTrxnSeq;
	private String wxCutsomerId;
	private String cardNo;
	private String trxnId;
	private String clientName;
	private String clientDesc;
	private String configName;
	private String reqMsg;
	private String rspMsg;
	private Date trxnDate;
	private String trxnMemo;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxSystemtrxnmemo() {
	}

	/** minimal constructor */
	public WxSystemtrxnmemo(WxSystemtrxnmemoId id, String wxCutsomerId,
			String cardNo) {
		this.id = id;
		this.wxCutsomerId = wxCutsomerId;
		this.cardNo = cardNo;
	}

	/** full constructor */
	public WxSystemtrxnmemo(WxSystemtrxnmemoId id, String wxTrxnSeq,
			String wxCutsomerId, String cardNo, String trxnId,
			String clientName, String clientDesc, String configName,
			String reqMsg, String rspMsg, Date trxnDate, String trxnMemo,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.wxTrxnSeq = wxTrxnSeq;
		this.wxCutsomerId = wxCutsomerId;
		this.cardNo = cardNo;
		this.trxnId = trxnId;
		this.clientName = clientName;
		this.clientDesc = clientDesc;
		this.configName = configName;
		this.reqMsg = reqMsg;
		this.rspMsg = rspMsg;
		this.trxnDate = trxnDate;
		this.trxnMemo = trxnMemo;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "trxnSeq", column = @Column(name = "TRXN_SEQ", nullable = false, length = 10)) })
	public WxSystemtrxnmemoId getId() {
		return this.id;
	}

	public void setId(WxSystemtrxnmemoId id) {
		this.id = id;
	}

	@Column(name = "WX_TRXN_SEQ", length = 10)
	public String getWxTrxnSeq() {
		return this.wxTrxnSeq;
	}

	public void setWxTrxnSeq(String wxTrxnSeq) {
		this.wxTrxnSeq = wxTrxnSeq;
	}

	@Column(name = "WX_CUTSOMER_ID", nullable = false, length = 40)
	public String getWxCutsomerId() {
		return this.wxCutsomerId;
	}

	public void setWxCutsomerId(String wxCutsomerId) {
		this.wxCutsomerId = wxCutsomerId;
	}

	@Column(name = "CARD_NO", length = 20)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "TRXN_ID", length = 10)
	public String getTrxnId() {
		return this.trxnId;
	}

	public void setTrxnId(String trxnId) {
		this.trxnId = trxnId;
	}

	@Column(name = "CLIENT_NAME", length = 10)
	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name = "CLIENT_DESC", length = 25)
	public String getClientDesc() {
		return this.clientDesc;
	}

	public void setClientDesc(String clientDesc) {
		this.clientDesc = clientDesc;
	}

	@Column(name = "CONFIG_NAME", length = 10)
	public String getConfigName() {
		return this.configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Column(name = "REQ_MSG", length = 1000)
	public String getReqMsg() {
		return this.reqMsg;
	}

	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}

	@Column(name = "RSP_MSG", length = 1000)
	public String getRspMsg() {
		return this.rspMsg;
	}

	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRXN_DATE", length = 7)
	public Date getTrxnDate() {
		return this.trxnDate;
	}

	public void setTrxnDate(Date trxnDate) {
		this.trxnDate = trxnDate;
	}

	@Column(name = "TRXN_MEMO", length = 100)
	public String getTrxnMemo() {
		return this.trxnMemo;
	}

	public void setTrxnMemo(String trxnMemo) {
		this.trxnMemo = trxnMemo;
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