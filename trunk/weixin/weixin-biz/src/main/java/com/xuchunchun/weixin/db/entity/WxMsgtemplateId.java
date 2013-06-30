package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxMsgtemplateId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxMsgtemplateId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String msgTempId;

	// Constructors

	/** default constructor */
	public WxMsgtemplateId() {
	}

	/** full constructor */
	public WxMsgtemplateId(Long bankorgId, String msgTempId) {
		this.bankorgId = bankorgId;
		this.msgTempId = msgTempId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "MSG_TEMP_ID", nullable = false, length = 10)
	public String getMsgTempId() {
		return this.msgTempId;
	}

	public void setMsgTempId(String msgTempId) {
		this.msgTempId = msgTempId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxMsgtemplateId))
			return false;
		WxMsgtemplateId castOther = (WxMsgtemplateId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getMsgTempId() == castOther.getMsgTempId()) || (this
						.getMsgTempId() != null
						&& castOther.getMsgTempId() != null && this
						.getMsgTempId().equals(castOther.getMsgTempId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getMsgTempId() == null ? 0 : this.getMsgTempId().hashCode());
		return result;
	}

}