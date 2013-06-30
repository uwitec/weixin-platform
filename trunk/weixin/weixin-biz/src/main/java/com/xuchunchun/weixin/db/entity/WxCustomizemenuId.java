package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxCustomizemenuId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxCustomizemenuId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String menuSeq;

	// Constructors

	/** default constructor */
	public WxCustomizemenuId() {
	}

	/** full constructor */
	public WxCustomizemenuId(Long bankorgId, String menuSeq) {
		this.bankorgId = bankorgId;
		this.menuSeq = menuSeq;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "MENU_SEQ", nullable = false, length = 10)
	public String getMenuSeq() {
		return this.menuSeq;
	}

	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxCustomizemenuId))
			return false;
		WxCustomizemenuId castOther = (WxCustomizemenuId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getMenuSeq() == castOther.getMenuSeq()) || (this
						.getMenuSeq() != null && castOther.getMenuSeq() != null && this
						.getMenuSeq().equals(castOther.getMenuSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getMenuSeq() == null ? 0 : this.getMenuSeq().hashCode());
		return result;
	}

}