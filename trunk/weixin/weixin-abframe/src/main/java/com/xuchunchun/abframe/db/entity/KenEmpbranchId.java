package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KenEmpbranchId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class KenEmpbranchId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String employeeId;
	private Long bankorgId;
	private String brBranchId;

	// Constructors

	/** default constructor */
	public KenEmpbranchId() {
	}

	/** full constructor */
	public KenEmpbranchId(String employeeId, Long bankorgId, String brBranchId) {
		this.employeeId = employeeId;
		this.bankorgId = bankorgId;
		this.brBranchId = brBranchId;
	}

	// Property accessors

	@Column(name = "EMPLOYEE_ID", nullable = false, length = 10)
	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "BR_BRANCH_ID", nullable = false, precision = 10, scale = 0)
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
		if (!(other instanceof KenEmpbranchId))
			return false;
		KenEmpbranchId castOther = (KenEmpbranchId) other;

		return ((this.getEmployeeId() == castOther.getEmployeeId()) || (this
				.getEmployeeId() != null && castOther.getEmployeeId() != null && this
				.getEmployeeId().equals(castOther.getEmployeeId())))
				&& ((this.getBankorgId() == castOther.getBankorgId()) || (this
						.getBankorgId() != null
						&& castOther.getBankorgId() != null && this
						.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getBrBranchId() == castOther.getBrBranchId()) || (this
						.getBrBranchId() != null
						&& castOther.getBrBranchId() != null && this
						.getBrBranchId().equals(castOther.getBrBranchId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEmployeeId() == null ? 0 : this.getEmployeeId()
						.hashCode());
		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37
				* result
				+ (getBrBranchId() == null ? 0 : this.getBrBranchId()
						.hashCode());
		return result;
	}

}