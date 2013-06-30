package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclExcludeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclExcludeId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String excludeId;
	private String systemId;

	// Constructors

	/** default constructor */
	public AclExcludeId() {
	}

	/** full constructor */
	public AclExcludeId(Long bankorgId, String excludeId, String systemId) {
		this.bankorgId = bankorgId;
		this.excludeId = excludeId;
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

	@Column(name = "EXCLUDE_ID", nullable = false, length = 40)
	public String getExcludeId() {
		return this.excludeId;
	}

	public void setExcludeId(String excludeId) {
		this.excludeId = excludeId;
	}

	@Column(name = "SYSTEM_ID", nullable = false, length = 20)
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
		if (!(other instanceof AclExcludeId))
			return false;
		AclExcludeId castOther = (AclExcludeId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getExcludeId() == castOther.getExcludeId()) || (this
						.getExcludeId() != null
						&& castOther.getExcludeId() != null && this
						.getExcludeId().equals(castOther.getExcludeId())))
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
				+ (getExcludeId() == null ? 0 : this.getExcludeId().hashCode());
		result = 37 * result
				+ (getSystemId() == null ? 0 : this.getSystemId().hashCode());
		return result;
	}

}