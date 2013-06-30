package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclChildsystemId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclChildsystemId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String systemId;

	// Constructors

	/** default constructor */
	public AclChildsystemId() {
	}

	/** full constructor */
	public AclChildsystemId(Long bankorgId, String systemId) {
		this.bankorgId = bankorgId;
		this.systemId = systemId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "SYSTEM_ID", nullable = false, length = 40)
	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclChildsystemId))
			return false;
		AclChildsystemId castOther = (AclChildsystemId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getSystemId() == castOther.getSystemId()) || (this
						.getSystemId() != null
						&& castOther.getSystemId() != null && this
						.getSystemId().equals(castOther.getSystemId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getSystemId() == null ? 0 : this.getSystemId().hashCode());
		return result;
	}

}