package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxFrdgrpprocmemoId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxFrdgrpprocmemoId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String groupId;
	private Long procSeq;

	// Constructors

	/** default constructor */
	public WxFrdgrpprocmemoId() {
	}

	/** full constructor */
	public WxFrdgrpprocmemoId(Long bankorgId, String groupId, Long procSeq) {
		this.bankorgId = bankorgId;
		this.groupId = groupId;
		this.procSeq = procSeq;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "GROUP_ID", nullable = false, length = 40)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "PROC_SEQ", nullable = false, precision = 10, scale = 0)
	public Long getProcSeq() {
		return this.procSeq;
	}

	public void setProcSeq(Long procSeq) {
		this.procSeq = procSeq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxFrdgrpprocmemoId))
			return false;
		WxFrdgrpprocmemoId castOther = (WxFrdgrpprocmemoId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null && castOther.getGroupId() != null && this
						.getGroupId().equals(castOther.getGroupId())))
				&& ((this.getProcSeq() == castOther.getProcSeq()) || (this
						.getProcSeq() != null && castOther.getProcSeq() != null && this
						.getProcSeq().equals(castOther.getProcSeq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		result = 37 * result
				+ (getProcSeq() == null ? 0 : this.getProcSeq().hashCode());
		return result;
	}

}