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
 * WxFrdgrpprocmemo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_FRDGRPPROCMEMO")
public class WxFrdgrpprocmemo implements java.io.Serializable {

	// Fields

	private WxFrdgrpprocmemoId id;
	private String procType;
	private String groupName;
	private Long userCount;
	private String preGroupName;
	private Long preUserCount;
	private Date procDate;
	private String procUser;
	private String procMemo;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxFrdgrpprocmemo() {
	}

	/** minimal constructor */
	public WxFrdgrpprocmemo(WxFrdgrpprocmemoId id) {
		this.id = id;
	}

	/** full constructor */
	public WxFrdgrpprocmemo(WxFrdgrpprocmemoId id, String procType,
			String groupName, Long userCount, String preGroupName,
			Long preUserCount, Date procDate, String procUser, String procMemo,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.procType = procType;
		this.groupName = groupName;
		this.userCount = userCount;
		this.preGroupName = preGroupName;
		this.preUserCount = preUserCount;
		this.procDate = procDate;
		this.procUser = procUser;
		this.procMemo = procMemo;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "groupId", column = @Column(name = "GROUP_ID", nullable = false, length = 40)),
			@AttributeOverride(name = "procSeq", column = @Column(name = "PROC_SEQ", nullable = false, precision = 10, scale = 0)) })
	public WxFrdgrpprocmemoId getId() {
		return this.id;
	}

	public void setId(WxFrdgrpprocmemoId id) {
		this.id = id;
	}

	@Column(name = "PROC_TYPE", length = 10)
	public String getProcType() {
		return this.procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	@Column(name = "GROUP_NAME", length = 40)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "USER_COUNT", precision = 10, scale = 0)
	public Long getUserCount() {
		return this.userCount;
	}

	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}

	@Column(name = "PRE_GROUP_NAME", length = 40)
	public String getPreGroupName() {
		return this.preGroupName;
	}

	public void setPreGroupName(String preGroupName) {
		this.preGroupName = preGroupName;
	}

	@Column(name = "PRE_USER_COUNT", precision = 10, scale = 0)
	public Long getPreUserCount() {
		return this.preUserCount;
	}

	public void setPreUserCount(Long preUserCount) {
		this.preUserCount = preUserCount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PROC_DATE", length = 7)
	public Date getProcDate() {
		return this.procDate;
	}

	public void setProcDate(Date procDate) {
		this.procDate = procDate;
	}

	@Column(name = "PROC_USER", length = 10)
	public String getProcUser() {
		return this.procUser;
	}

	public void setProcUser(String procUser) {
		this.procUser = procUser;
	}

	@Column(name = "PROC_MEMO", length = 10)
	public String getProcMemo() {
		return this.procMemo;
	}

	public void setProcMemo(String procMemo) {
		this.procMemo = procMemo;
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