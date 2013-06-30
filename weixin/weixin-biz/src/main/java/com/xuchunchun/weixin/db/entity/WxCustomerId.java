package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxCustomerId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxCustomerId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String wxCustomerId;

	// Constructors

	/** default constructor */
	public WxCustomerId() {
	}

	/** full constructor */
	public WxCustomerId(Long bankorgId, String wxCustomerId) {
		this.bankorgId = bankorgId;
		this.wxCustomerId = wxCustomerId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "WX_CUSTOMER_ID", nullable = false, length = 40)
	public String getWxCustomerId() {
		return this.wxCustomerId;
	}

	public void setWxCustomerId(String wxCustomerId) {
		this.wxCustomerId = wxCustomerId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxCustomerId))
			return false;
		WxCustomerId castOther = (WxCustomerId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getWxCustomerId() == castOther.getWxCustomerId()) || (this
						.getWxCustomerId() != null
						&& castOther.getWxCustomerId() != null && this
						.getWxCustomerId().equals(castOther.getWxCustomerId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37
				* result
				+ (getWxCustomerId() == null ? 0 : this.getWxCustomerId()
						.hashCode());
		return result;
	}

}