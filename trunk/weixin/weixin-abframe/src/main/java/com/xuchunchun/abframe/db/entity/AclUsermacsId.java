package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclUsermacsId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclUsermacsId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String userId;
	private String maccode;

	// Constructors

	/** default constructor */
	public AclUsermacsId() {
	}

	/** full constructor */
	public AclUsermacsId(String userId, String maccode) {
		this.userId = userId;
		this.maccode = maccode;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "MACCODE", nullable = false, length = 40)
	public String getMaccode() {
		return this.maccode;
	}

	public void setMaccode(String maccode) {
		this.maccode = maccode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclUsermacsId))
			return false;
		AclUsermacsId castOther = (AclUsermacsId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getMaccode() == castOther.getMaccode()) || (this
						.getMaccode() != null && castOther.getMaccode() != null && this
						.getMaccode().equals(castOther.getMaccode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getMaccode() == null ? 0 : this.getMaccode().hashCode());
		return result;
	}

}