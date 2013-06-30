package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclLoginstrategyId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclLoginstrategyId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String loginStrategy;

	// Constructors

	/** default constructor */
	public AclLoginstrategyId() {
	}

	/** full constructor */
	public AclLoginstrategyId(Long bankorgId, String loginStrategy) {
		this.bankorgId = bankorgId;
		this.loginStrategy = loginStrategy;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "LOGIN_STRATEGY", nullable = false, length = 10)
	public String getLoginStrategy() {
		return this.loginStrategy;
	}

	public void setLoginStrategy(String loginStrategy) {
		this.loginStrategy = loginStrategy;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclLoginstrategyId))
			return false;
		AclLoginstrategyId castOther = (AclLoginstrategyId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getLoginStrategy() == castOther.getLoginStrategy()) || (this
						.getLoginStrategy() != null
						&& castOther.getLoginStrategy() != null && this
						.getLoginStrategy()
						.equals(castOther.getLoginStrategy())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37
				* result
				+ (getLoginStrategy() == null ? 0 : this.getLoginStrategy()
						.hashCode());
		return result;
	}

}