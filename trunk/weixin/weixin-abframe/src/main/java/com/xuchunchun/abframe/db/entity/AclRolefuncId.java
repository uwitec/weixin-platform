package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclRolefuncId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclRolefuncId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String roldId;
	private String functionId;

	// Constructors

	/** default constructor */
	public AclRolefuncId() {
	}

	/** full constructor */
	public AclRolefuncId(Long bankorgId, String roldId, String functionId) {
		this.bankorgId = bankorgId;
		this.roldId = roldId;
		this.functionId = functionId;
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

	@Column(name = "FUNCTION_ID", nullable = false, length = 20)
	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclRolefuncId))
			return false;
		AclRolefuncId castOther = (AclRolefuncId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getRoldId() == castOther.getRoldId()) || (this
						.getRoldId() != null && castOther.getRoldId() != null && this
						.getRoldId().equals(castOther.getRoldId())))
				&& ((this.getFunctionId() == castOther.getFunctionId()) || (this
						.getFunctionId() != null
						&& castOther.getFunctionId() != null && this
						.getFunctionId().equals(castOther.getFunctionId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getRoldId() == null ? 0 : this.getRoldId().hashCode());
		result = 37
				* result
				+ (getFunctionId() == null ? 0 : this.getFunctionId()
						.hashCode());
		return result;
	}

}