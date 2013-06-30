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
 * WxMenuprocmemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_MENUPROCMEMO")
public class WxMenuprocmemo implements java.io.Serializable {

	// Fields

	private WxMenuprocmemoId id;
	private Date procDate;
	private String procUser;
	private String procType;
	private String procMemo;
	private String errorCode;
	private String errorMsg;
	private String errorMsgCn;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxMenuprocmemo() {
	}

	/** minimal constructor */
	public WxMenuprocmemo(WxMenuprocmemoId id) {
		this.id = id;
	}

	/** full constructor */
	public WxMenuprocmemo(WxMenuprocmemoId id, Date procDate, String procUser,
			String procType, String procMemo, String errorCode,
			String errorMsg, String errorMsgCn, Date modiDate, String modiUser,
			Integer version) {
		this.id = id;
		this.procDate = procDate;
		this.procUser = procUser;
		this.procType = procType;
		this.procMemo = procMemo;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorMsgCn = errorMsgCn;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "memoSeq", column = @Column(name = "MEMO_SEQ", nullable = false, length = 20)) })
	public WxMenuprocmemoId getId() {
		return this.id;
	}

	public void setId(WxMenuprocmemoId id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PROC_DATE", length = 7)
	public Date getProcDate() {
		return this.procDate;
	}

	public void setProcDate(Date procDate) {
		this.procDate = procDate;
	}

	@Column(name = "PROC_USER", length = 20)
	public String getProcUser() {
		return this.procUser;
	}

	public void setProcUser(String procUser) {
		this.procUser = procUser;
	}

	@Column(name = "PROC_TYPE", length = 20)
	public String getProcType() {
		return this.procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	@Column(name = "PROC_MEMO", length = 200)
	public String getProcMemo() {
		return this.procMemo;
	}

	public void setProcMemo(String procMemo) {
		this.procMemo = procMemo;
	}

	@Column(name = "ERROR_CODE", length = 10)
	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Column(name = "ERROR_MSG", length = 100)
	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Column(name = "ERROR_MSG_CN", length = 100)
	public String getErrorMsgCn() {
		return this.errorMsgCn;
	}

	public void setErrorMsgCn(String errorMsgCn) {
		this.errorMsgCn = errorMsgCn;
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