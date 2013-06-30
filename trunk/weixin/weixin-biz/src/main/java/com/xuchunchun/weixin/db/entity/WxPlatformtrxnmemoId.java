package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxPlatformtrxnmemoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxPlatformtrxnmemoId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String trxnSeq;

	// Constructors

	/** default constructor */
	public WxPlatformtrxnmemoId() {
	}

	/** full constructor */
	public WxPlatformtrxnmemoId(Long bankorgId, String trxnSeq) {
		this.bankorgId = bankorgId;
		this.trxnSeq = trxnSeq;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "TRXN_SEQ", nullable = false, length = 10)
	public String getTrxnSeq() {
		return this.trxnSeq;
	}

	public void setTrxnSeq(String trxnSeq) {
		this.trxnSeq = trxnSeq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxPlatformtrxnmemoId))
			return false;
		WxPlatformtrxnmemoId castOther = (WxPlatformtrxnmemoId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getTrxnSeq() == castOther.getTrxnSeq()) || (this
						.getTrxnSeq() != null && castOther.getTrxnSeq() != null && this
						.getTrxnSeq().equals(castOther.getTrxnSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getTrxnSeq() == null ? 0 : this.getTrxnSeq().hashCode());
		return result;
	}

}