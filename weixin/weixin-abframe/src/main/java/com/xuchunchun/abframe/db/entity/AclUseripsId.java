package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclUseripsId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclUseripsId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String userId;
	private String ipaddress;

	// Constructors

	/** default constructor */
	public AclUseripsId() {
	}

	/** full constructor */
	public AclUseripsId(String userId, String ipaddress) {
		this.userId = userId;
		this.ipaddress = ipaddress;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "IPADDRESS", nullable = false, length = 40)
	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclUseripsId))
			return false;
		AclUseripsId castOther = (AclUseripsId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getIpaddress() == castOther.getIpaddress()) || (this
						.getIpaddress() != null
						&& castOther.getIpaddress() != null && this
						.getIpaddress().equals(castOther.getIpaddress())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getIpaddress() == null ? 0 : this.getIpaddress().hashCode());
		return result;
	}

}