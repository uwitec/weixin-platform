package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclUserlistId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclUserlistId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private String userId;
	private String listType;

	// Constructors

	/** default constructor */
	public AclUserlistId() {
	}

	/** full constructor */
	public AclUserlistId(String userId, String listType) {
		this.userId = userId;
		this.listType = listType;
	}

	// Property accessors

	@Column(name = "USER_ID", nullable = false, length = 10)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "LIST_TYPE", nullable = false, length = 1)
	public String getListType() {
		return this.listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclUserlistId))
			return false;
		AclUserlistId castOther = (AclUserlistId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getListType() == castOther.getListType()) || (this
						.getListType() != null
						&& castOther.getListType() != null && this
						.getListType().equals(castOther.getListType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getListType() == null ? 0 : this.getListType().hashCode());
		return result;
	}

}