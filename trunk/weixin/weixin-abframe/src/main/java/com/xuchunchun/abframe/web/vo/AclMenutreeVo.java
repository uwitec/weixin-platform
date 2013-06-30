package com.xuchunchun.abframe.web.vo;

import com.xuchunchun.abframe.db.entity.AclMenutreeId;

public class AclMenutreeVo {
	private AclMenutreeId id;
	private Long nodeSeq;
	private String nodeName;
	private String parentId;
	private String isLeaf;
	private String functionId;
	private String menuType;
	public AclMenutreeId getId() {
		return id;
	}
	public void setId(AclMenutreeId id) {
		this.id = id;
	}
	public Long getNodeSeq() {
		return nodeSeq;
	}
	public void setNodeSeq(Long nodeSeq) {
		this.nodeSeq = nodeSeq;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public AclMenutreeVo(AclMenutreeId id, Long nodeSeq, String nodeName,
			String parentId, String isLeaf, String functionId, String menuType) {
		super();
		this.id = id;
		this.nodeSeq = nodeSeq;
		this.nodeName = nodeName;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		this.functionId = functionId;
		this.menuType = menuType;
	}
	public AclMenutreeVo() {
		super();
	}
	
}
