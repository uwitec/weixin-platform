package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclFuncexcludeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclFuncexcludeId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String functionId;
	private String excludeId;

	// Constructors

	/** default constructor */
	public AclFuncexcludeId() {
	}

	/** full constructor */
	public AclFuncexcludeId(Long bankorgId, String functionId, String excludeId) {
		this.bankorgId = bankorgId;
		this.functionId = functionId;
		this.excludeId = excludeId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "FUNCTION_ID", nullable = false, length = 20)
	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Column(name = "EXCLUDE_ID", nullable = false, length = 40)
	public String getExcludeId() {
		return this.excludeId;
	}

	public void setExcludeId(String excludeId) {
		this.excludeId = excludeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclFuncexcludeId))
			return false;
		AclFuncexcludeId castOther = (AclFuncexcludeId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getFunctionId() == castOther.getFunctionId()) || (this
						.getFunctionId() != null
						&& castOther.getFunctionId() != null && this
						.getFunctionId().equals(castOther.getFunctionId())))
				&& ((this.getExcludeId() == castOther.getExcludeId()) || (this
						.getExcludeId() != null
						&& castOther.getExcludeId() != null && this
						.getExcludeId().equals(castOther.getExcludeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37
				* result
				+ (getFunctionId() == null ? 0 : this.getFunctionId()
						.hashCode());
		result = 37 * result
				+ (getExcludeId() == null ? 0 : this.getExcludeId().hashCode());
		return result;
	}

}