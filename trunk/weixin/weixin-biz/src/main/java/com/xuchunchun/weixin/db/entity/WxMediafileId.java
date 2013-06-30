package com.xuchunchun.weixin.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * WxMediafileId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class WxMediafileId implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String mediaId;

	// Constructors

	/** default constructor */
	public WxMediafileId() {
	}

	/** full constructor */
	public WxMediafileId(Long bankorgId, String mediaId) {
		this.bankorgId = bankorgId;
		this.mediaId = mediaId;
	}

	// Property accessors

	@Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "MEDIA_ID", nullable = false, length = 40)
	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WxMediafileId))
			return false;
		WxMediafileId castOther = (WxMediafileId) other;

		return ((this.getBankorgId() == castOther.getBankorgId()) || (this
				.getBankorgId() != null && castOther.getBankorgId() != null && this
				.getBankorgId().equals(castOther.getBankorgId())))
				&& ((this.getMediaId() == castOther.getMediaId()) || (this
						.getMediaId() != null && castOther.getMediaId() != null && this
						.getMediaId().equals(castOther.getMediaId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankorgId() == null ? 0 : this.getBankorgId().hashCode());
		result = 37 * result
				+ (getMediaId() == null ? 0 : this.getMediaId().hashCode());
		return result;
	}

}