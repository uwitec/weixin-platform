package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxCardId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxCardId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String cardNo;

	// Constructors

	/** default constructor */
	public WxCardId() {
	}

	/** full constructor */
	public WxCardId(Long bankorgId, String cardNo) {
		this.bankorgId = bankorgId;
		this.cardNo = cardNo;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "CARD_NO", nullable = false, length = 20)
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxCardId))
			return false;
		WxCardId castOther = (WxCardId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getCardNo() == castOther.getCardNo()) || (this
						.getCardNo() != null && castOther.getCardNo() != null && this
						.getCardNo().equals(castOther.getCardNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getCardNo() == null ? 0 : this.getCardNo().hashCode());
		return result;
	}

}