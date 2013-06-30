package com.xuchunchun.abframe.bean;

import java.util.Date;

import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclMenutreeId;


public class AclMenuTreeForm extends AclMenutree{
	private static final long serialVersionUID = 1L;
	
	private Long bankorgId;
	private String nodeId;
	private Long nodeSeq;
	private String nodeName;
	private String parentId;
	private String isLeaf;
	private String functionId;
	private Date modiDate;
	private String modiUser;
	private Integer version;
	
	public AclMenuTreeForm(){}
	
	public AclMenuTreeForm(AclMenutree aclMenutree){
		bankorgId = aclMenutree.getId().getBankorgId();
		nodeId = aclMenutree.getId().getNodeId();
		nodeSeq = aclMenutree.getNodeSeq();
		nodeName = aclMenutree.getNodeName();
		parentId = aclMenutree.getParentId();
		isLeaf = aclMenutree.getIsLeaf();
		functionId = aclMenutree.getFunctionId();
	}
	
	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}
	public Long getBankorgId() {
		return bankorgId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeId() {
		return nodeId;
	}
}
