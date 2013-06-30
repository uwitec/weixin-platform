package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxMenuprocmemoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxMenuprocmemoId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String memoSeq;

	// Constructors

	/** default constructor */
	public WxMenuprocmemoId() {
	}

	/** full constructor */
	public WxMenuprocmemoId(Long bankorgId, String memoSeq) {
		this.bankorgId = bankorgId;
		this.memoSeq = memoSeq;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "MEMO_SEQ", nullable = false, length = 20)
	public String getMemoSeq() {
		return this.memoSeq;
	}

	public void setMemoSeq(String memoSeq) {
		this.memoSeq = memoSeq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxMenuprocmemoId))
			return false;
		WxMenuprocmemoId castOther = (WxMenuprocmemoId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getMemoSeq() == castOther.getMemoSeq()) || (this
						.getMemoSeq() != null && castOther.getMemoSeq() != null && this
						.getMemoSeq().equals(castOther.getMemoSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getMemoSeq() == null ? 0 : this.getMemoSeq().hashCode());
		return result;
	}

}