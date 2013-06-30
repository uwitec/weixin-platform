package com.xuchunchun.weixin.db.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WxMediafile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_MEDIAFILE")
public class WxMediafile implements java.io.Serializable {

	// Fields

	private WxMediafileId id;
	private String mediaType;
	private Date uploadDate;
	private Date expiryDate;
	private String fileDeac;
	private String uploadUser;
	private String uploadMemo;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxMediafile() {
	}

	/** minimal constructor */
	public WxMediafile(WxMediafileId id) {
		this.id = id;
	}

	/** full constructor */
	public WxMediafile(WxMediafileId id, String mediaType, Date uploadDate,
			Date expiryDate, String fileDeac, String uploadUser,
			String uploadMemo, Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.mediaType = mediaType;
		this.uploadDate = uploadDate;
		this.expiryDate = expiryDate;
		this.fileDeac = fileDeac;
		this.uploadUser = uploadUser;
		this.uploadMemo = uploadMemo;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "mediaId", column = @Column(name = "MEDIA_ID", nullable = false, length = 40)) })
	public WxMediafileId getId() {
		return this.id;
	}

	public void setId(WxMediafileId id) {
		this.id = id;
	}

	@Column(name = "MEDIA_TYPE", length = 10)
	public String getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPLOAD_DATE", length = 7)
	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRY_DATE", length = 7)
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "FILE_DEAC", length = 100)
	public String getFileDeac() {
		return this.fileDeac;
	}

	public void setFileDeac(String fileDeac) {
		this.fileDeac = fileDeac;
	}

	@Column(name = "UPLOAD_USER", length = 20)
	public String getUploadUser() {
		return this.uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	@Column(name = "UPLOAD_MEMO", length = 100)
	public String getUploadMemo() {
		return this.uploadMemo;
	}

	public void setUploadMemo(String uploadMemo) {
		this.uploadMemo = uploadMemo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODI_DATE", length = 7)
	public Date getModiDate() {
		return this.modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	@Column(name = "MODI_USER", length = 64)
	public String getModiUser() {
		return this.modiUser;
	}

	public void setModiUser(String modiUser) {
		this.modiUser = modiUser;
	}

	@Column(name = "VERSION", precision = 8, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}