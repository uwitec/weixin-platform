package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclPasswordId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclPasswordId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String userId;
	private Long pwdSeq;

	// Constructors

	/** default constructor */
	public AclPasswordId() {
	}

	/** full constructor */
	public AclPasswordId(String userId, Long pwdSeq) {
		this.userId = userId;
		this.pwdSeq = pwdSeq;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "PWD_SEQ", nullable = false, precision = 10, scale = 0)
	public Long getPwdSeq() {
		return this.pwdSeq;
	}

	public void setPwdSeq(Long pwdSeq) {
		this.pwdSeq = pwdSeq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclPasswordId))
			return false;
		AclPasswordId castOther = (AclPasswordId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getPwdSeq() == castOther.getPwdSeq()) || (this
						.getPwdSeq() != null && castOther.getPwdSeq() != null && this
						.getPwdSeq().equals(castOther.getPwdSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getPwdSeq() == null ? 0 : this.getPwdSeq().hashCode());
		return result;
	}

}