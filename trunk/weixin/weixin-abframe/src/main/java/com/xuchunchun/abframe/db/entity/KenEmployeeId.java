package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KenEmployeeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class KenEmployeeId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String employeeId;

	// Constructors

	/** default constructor */
	public KenEmployeeId() {
	}

	/** full constructor */
	public KenEmployeeId(Long bankorgId, String employeeId) {
		this.bankorgId = bankorgId;
		this.employeeId = employeeId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "EMPLOYEE_ID", nullable = false, length = 10)
	public String getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KenEmployeeId))
			return false;
		KenEmployeeId castOther = (KenEmployeeId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getEmployeeId() == castOther.getEmployeeId()) || (this
						.getEmployeeId() != null
						&& castOther.getEmployeeId() != null && this
						.getEmployeeId().equals(castOther.getEmployeeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37
				* result
				+ (getEmployeeId() == null ? 0 : this.getEmployeeId()
						.hashCode());
		return result;
	}

}