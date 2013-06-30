package com.xuchunchun.abframe.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AclMenutreeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AclMenutreeId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private Long bankorgId;
	private String nodeId;

	// Constructors

	/** default constructor */
	public AclMenutreeId() {
	}

	/** full constructor */
	public AclMenutreeId(Long bankorgId, String nodeId) {
		this.bankorgId = bankorgId;
		this.nodeId = nodeId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "NODE_ID", nullable = false, length = 20)
	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AclMenutreeId))
			return false;
		AclMenutreeId castOther = (AclMenutreeId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getNodeId() == castOther.getNodeId()) || (this
						.getNodeId() != null && castOther.getNodeId() != null && this
						.getNodeId().equals(castOther.getNodeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getNodeId() == null ? 0 : this.getNodeId().hashCode());
		return result;
	}

}