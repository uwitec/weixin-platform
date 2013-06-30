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
 * WxPlatformtrxnmemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_PLATFORMTRXNMEMO")
public class WxPlatformtrxnmemo implements java.io.Serializable {

	// Fields

	private WxPlatformtrxnmemoId id;
	private String wxCutsomerId;
	private String platformId;
	private String pfAnotherId;
	private String cardNo;
	private String msgType;
	private String msgKey;
	private String trxnId;
	private String rspMsgType;
	private String clientName;
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
	public WxPlatformtrxnmemo() {
	}

	/** minimal constructor */
	public WxPlatformtrxnmemo(WxPlatformtrxnmemoId id, String wxCutsomerId,
			String cardNo, String msgType, String msgKey) {
		this.id = id;
		this.wxCutsomerId = wxCutsomerId;
		this.cardNo = cardNo;
		this.msgType = msgType;
		this.msgKey = msgKey;
	}

	/** full constructor */
	public WxPlatformtrxnmemo(WxPlatformtrxnmemoId id, String wxCutsomerId,
			String platformId, String pfAnotherId, String cardNo,
			String msgType, String msgKey, String trxnId, String rspMsgType,
			String clientName, String configName, String reqMsg, String rspMsg,
			Date trxnDate, String trxnMemo, Date modiDate, String modiUser,
			Integer version) {
		this.id = id;
		this.wxCutsomerId = wxCutsomerId;
		this.platformId = platformId;
		this.pfAnotherId = pfAnotherId;
		this.cardNo = cardNo;
		this.msgType = msgType;
		this.msgKey = msgKey;
		this.trxnId = trxnId;
		this.rspMsgType = rspMsgType;
		this.clientName = clientName;
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
	public WxPlatformtrxnmemoId getId() {
		return this.id;
	}

	public void setId(WxPlatformtrxnmemoId id) {
		this.id = id;
	}

	@Column(name = "WX_CUTSOMER_ID", nullable = false, length = 40)
	public String getWxCutsomerId() {
		return this.wxCutsomerId;
	}

	public void setWxCutsomerId(String wxCutsomerId) {
		this.wxCutsomerId = wxCutsomerId;
	}

	@Column(name = "PLATFORM_ID", length = 40)
	public String getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	@Column(name = "PF_ANOTHER_ID", length = 40)
	public String getPfAnotherId() {
		return this.pfAnotherId;
	}

	public void setPfAnotherId(String pfAnotherId) {
		this.pfAnotherId = pfAnotherId;
	}

	@Column(name = "CARD_NO", length = 20)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "MSG_TYPE", length = 10)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "MSG_KEY", length = 100)
	public String getMsgKey() {
		return this.msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
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