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
 * WxFriendgroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_FRIENDGROUP")
public class WxFriendgroup implements java.io.Serializable {

	// Fields

	private WxFriendgroupId id;
	private String groupName;
	private Long userCount;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxFriendgroup() {
	}

	/** minimal constructor */
	public WxFriendgroup(WxFriendgroupId id) {
		this.id = id;
	}

	/** full constructor */
	public WxFriendgroup(WxFriendgroupId id, String groupName, Long userCount,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.groupName = groupName;
		this.userCount = userCount;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "groupId", column = @Column(name = "GROUP_ID", nullable = false, length = 40)) })
	public WxFriendgroupId getId() {
		return this.id;
	}

	public void setId(WxFriendgroupId id) {
		this.id = id;
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