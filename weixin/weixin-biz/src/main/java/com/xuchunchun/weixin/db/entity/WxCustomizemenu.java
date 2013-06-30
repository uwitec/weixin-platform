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
 * WxCustomizemenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_CUSTOMIZEMENU")
public class WxCustomizemenu implements java.io.Serializable {

	// Fields

	private WxCustomizemenuId id;
	private String menuKey;
	private String menuName;
	private String menuType;
	private Long menuLevel;
	private String parentMenuSeq;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxCustomizemenu() {
	}

	/** minimal constructor */
	public WxCustomizemenu(WxCustomizemenuId id) {
		this.id = id;
	}

	/** full constructor */
	public WxCustomizemenu(WxCustomizemenuId id, String menuKey,
			String menuName, String menuType, Long menuLevel,
			String parentMenu, Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.menuKey = menuKey;
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuLevel = menuLevel;
		this.parentMenuSeq = parentMenu;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "menuSeq", column = @Column(name = "MENU_SEQ", nullable = false, length = 10)) })
	public WxCustomizemenuId getId() {
		return this.id;
	}

	public void setId(WxCustomizemenuId id) {
		this.id = id;
	}

	@Column(name = "MENU_KEY", length = 128)
	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	@Column(name = "MENU_NAME", length = 40)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_TYPE", length = 20)
	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "MENU_LEVEL", precision = 10, scale = 0)
	public Long getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
	}

	@Column(name = "PARENT_MENU_SEQ", length = 10)
	public String getParentMenuSeq() {
		return this.parentMenuSeq;
	}

	public void setParentMenuSeq(String parentMenuSeq) {
		this.parentMenuSeq = parentMenuSeq;
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