package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KenBankbranchId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class KenBankbranchId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String brBranchId;

	// Constructors

	/** default constructor */
	public KenBankbranchId() {
	}

	/** full constructor */
	public KenBankbranchId(Long bankorgId, String brBranchId) {
		this.bankorgId = bankorgId;
		this.brBranchId = brBranchId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "BR_BRANCH_ID", nullable = false, length = 10)
	public String getBrBranchId() {
		return this.brBranchId;
	}

	public void setBrBranchId(String brBranchId) {
		this.brBranchId = brBranchId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KenBankbranchId))
			return false;
		KenBankbranchId castOther = (KenBankbranchId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getBrBranchId() == castOther.getBrBranchId()) || (this
						.getBrBranchId() != null
						&& castOther.getBrBranchId() != null && this
						.getBrBranchId().equals(castOther.getBrBranchId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37
				* result
				+ (getBrBranchId() == null ? 0 : this.getBrBranchId()
						.hashCode());
		return result;
	}

}