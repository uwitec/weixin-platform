package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclRoleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclRoleId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String roldId;

	// Constructors

	/** default constructor */
	public AclRoleId() {
	}

	/** full constructor */
	public AclRoleId(Long bankorgId, String roldId) {
		this.bankorgId = bankorgId;
		this.roldId = roldId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "ROLD_ID", nullable = false, length = 20)
	public String getRoldId() {
		return this.roldId;
	}

	public void setRoldId(String roldId) {
		this.roldId = roldId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclRoleId))
			return false;
		AclRoleId castOther = (AclRoleId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getRoldId() == castOther.getRoldId()) || (this
						.getRoldId() != null && castOther.getRoldId() != null && this
						.getRoldId().equals(castOther.getRoldId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getRoldId() == null ? 0 : this.getRoldId().hashCode());
		return result;
	}

}