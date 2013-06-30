package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclUserroleId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclUserroleId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String userId;
	private String roldId;

	// Constructors

	/** default constructor */
	public AclUserroleId() {
	}

	/** full constructor */
	public AclUserroleId(String userId, String roldId) {
		this.userId = userId;
		this.roldId = roldId;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ROLD_ID", nullable = false, length = 10)
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
		if (!(other instanceof AclUserroleId))
			return false;
		AclUserroleId castOther = (AclUserroleId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getRoldId() == castOther.getRoldId()) || (this
						.getRoldId() != null && castOther.getRoldId() != null && this
						.getRoldId().equals(castOther.getRoldId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoldId() == null ? 0 : this.getRoldId().hashCode());
		return result;
	}

}