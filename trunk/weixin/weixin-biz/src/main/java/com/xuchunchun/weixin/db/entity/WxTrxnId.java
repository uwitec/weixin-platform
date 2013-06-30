package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxTrxnId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxTrxnId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String trxnId;

	// Constructors

	/** default constructor */
	public WxTrxnId() {
	}

	/** full constructor */
	public WxTrxnId(Long bankorgId, String trxnId) {
		this.bankorgId = bankorgId;
		this.trxnId = trxnId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "TRXN_ID", nullable = false, length = 10)
	public String getTrxnId() {
		return this.trxnId;
	}

	public void setTrxnId(String trxnId) {
		this.trxnId = trxnId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxTrxnId))
			return false;
		WxTrxnId castOther = (WxTrxnId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getTrxnId() == castOther.getTrxnId()) || (this
						.getTrxnId() != null && castOther.getTrxnId() != null && this
						.getTrxnId().equals(castOther.getTrxnId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getTrxnId() == null ? 0 : this.getTrxnId().hashCode());
		return result;
	}

}