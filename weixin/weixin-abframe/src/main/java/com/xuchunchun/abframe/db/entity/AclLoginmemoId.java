package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclLoginmemoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclLoginmemoId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String userId;
	private Long loginSeq;

	// Constructors

	/** default constructor */
	public AclLoginmemoId() {
	}

	/** full constructor */
	public AclLoginmemoId(String userId, Long loginSeq) {
		this.userId = userId;
		this.loginSeq = loginSeq;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "LOGIN_SEQ", nullable = false, precision = 10, scale = 0)
	public Long getLoginSeq() {
		return this.loginSeq;
	}

	public void setLoginSeq(Long loginSeq) {
		this.loginSeq = loginSeq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclLoginmemoId))
			return false;
		AclLoginmemoId castOther = (AclLoginmemoId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getLoginSeq() == castOther.getLoginSeq()) || (this
						.getLoginSeq() != null
						&& castOther.getLoginSeq() != null && this
						.getLoginSeq().equals(castOther.getLoginSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getLoginSeq() == null ? 0 : this.getLoginSeq().hashCode());
		return result;
	}

}