package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxTrxnmappingId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxTrxnmappingId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String msgType;
	private String msgKey;

	// Constructors

	/** default constructor */
	public WxTrxnmappingId() {
	}

	/** full constructor */
	public WxTrxnmappingId(Long bankorgId, String msgType, String msgKey) {
		this.bankorgId = bankorgId;
		this.msgType = msgType;
		this.msgKey = msgKey;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "MSG_TYPE", nullable = false, length = 10)
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "MSG_KEY", nullable = false, length = 100)
	public String getMsgKey() {
		return this.msgKey;
	}

	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxTrxnmappingId))
			return false;
		WxTrxnmappingId castOther = (WxTrxnmappingId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getMsgType() == castOther.getMsgType()) || (this
						.getMsgType() != null && castOther.getMsgType() != null && this
						.getMsgType().equals(castOther.getMsgType())))
				&& ((this.getMsgKey() == castOther.getMsgKey()) || (this
						.getMsgKey() != null && castOther.getMsgKey() != null && this
						.getMsgKey().equals(castOther.getMsgKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getMsgType() == null ? 0 : this.getMsgType().hashCode());
		result = 37 * result
				+ (getMsgKey() == null ? 0 : this.getMsgKey().hashCode());
		return result;
	}

}