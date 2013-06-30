package com.xuchunchun.abframe.db.entity;

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
 * AclMenutree entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_MENUTREE")
public class AclMenutree implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclMenutreeId id;
	private Long nodeSeq;
	private String nodeName;
	private String parentId;
	private String isLeaf;
	private String functionId;
	private String menuType;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclMenutree() {
	}

	/** minimal constructor */
	public AclMenutree(AclMenutreeId id) {
		this.id = id;
	}

	/** full constructor */
	public AclMenutree(AclMenutreeId id, Long nodeSeq, String nodeName,
			String parentId, String isLeaf, String functionId, String menuType, Date modiDate,
			String modiUser, Integer version) {
		this.id = id;
		this.nodeSeq = nodeSeq;
		this.nodeName = nodeName;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		this.functionId = functionId;
		this.menuType = menuType;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "nodeId", column = @Column(name = "NODE_ID", nullable = false, length = 20)) })
	public AclMenutreeId getId() {
		return this.id;
	}

	public void setId(AclMenutreeId id) {
		this.id = id;
	}

	@Column(name = "NODE_SEQ", precision = 10, scale = 0)
	public Long getNodeSeq() {
		return this.nodeSeq;
	}

	public void setNodeSeq(Long nodeSeq) {
		this.nodeSeq = nodeSeq;
	}

	@Column(name = "NODE_NAME", length = 40)
	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	@Column(name = "PARENT_ID", length = 20)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "IS_LEAF", length = 1)
	public String getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "FUNCTION_ID", length = 20)
	public String getFunctionId() {
		return this.functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	@Column(name = "MENU_TYPE", length = 1)
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
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