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
 * WxMsgtemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_MSGTEMPLATE")
public class WxMsgtemplate implements java.io.Serializable {

	// Fields

	private WxMsgtemplateId id;
	private String msgTempName;
	private String msgTempType;
	private String content;
	private String musicUrl;
	private String hqMusicUrl;
	private String title;
	private String description;
	private String picUrl;
	private String url;
	private Date modiDate;
	private String modiUser;
	private Integer version;
	private String loopVar;

	// Constructors

	/** default constructor */
	public WxMsgtemplate() {
	}

	/** minimal constructor */
	public WxMsgtemplate(WxMsgtemplateId id) {
		this.id = id;
	}

	/** full constructor */
	public WxMsgtemplate(WxMsgtemplateId id, String msgTempName,
			String msgTempType, String content, String musicUrl,
			String hqMusicUrl, String title, String description, String picUrl,
			String url, Date modiDate, String modiUser, Integer version,
			String loopVar) {
		this.id = id;
		this.msgTempName = msgTempName;
		this.msgTempType = msgTempType;
		this.content = content;
		this.musicUrl = musicUrl;
		this.hqMusicUrl = hqMusicUrl;
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
		this.loopVar = loopVar;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "msgTempId", column = @Column(name = "MSG_TEMP_ID", nullable = false, length = 10)) })
	public WxMsgtemplateId getId() {
		return this.id;
	}

	public void setId(WxMsgtemplateId id) {
		this.id = id;
	}

	@Column(name = "MSG_TEMP_NAME", length = 40)
	public String getMsgTempName() {
		return this.msgTempName;
	}

	public void setMsgTempName(String msgTempName) {
		this.msgTempName = msgTempName;
	}

	@Column(name = "MSG_TEMP_TYPE", length = 10)
	public String getMsgTempType() {
		return this.msgTempType;
	}

	public void setMsgTempType(String msgTempType) {
		this.msgTempType = msgTempType;
	}

	@Column(name = "CONTENT", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "MUSIC_URL", length = 100)
	public String getMusicUrl() {
		return this.musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	@Column(name = "HQ_MUSIC_URL", length = 100)
	public String getHqMusicUrl() {
		return this.hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	@Column(name = "TITLE", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PIC_URL", length = 100)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	@Column(name = "LOOP_VAR", length = 20)
	public String getLoopVar() {
		return this.loopVar;
	}

	public void setLoopVar(String loopVar) {
		this.loopVar = loopVar;
	}

}