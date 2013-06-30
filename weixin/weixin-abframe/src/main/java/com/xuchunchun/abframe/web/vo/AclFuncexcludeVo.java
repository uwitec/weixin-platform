package com.xuchunchun.abframe.web.vo;

import com.xuchunchun.abframe.db.entity.AclFuncexcludeId;

/**
 * 功能例外VO
 * @author wangyang
 *
 */
public class AclFuncexcludeVo {
	private AclFuncexcludeId id;
	private String excludeType;
	public AclFuncexcludeId getId() {
		return id;
	}
	public void setId(AclFuncexcludeId id) {
		this.id = id;
	}
	public String getExcludeType() {
		return excludeType;
	}
	public void setExcludeType(String excludeType) {
		this.excludeType = excludeType;
	}
	public AclFuncexcludeVo(AclFuncexcludeId id, String excludeType) {
		super();
		this.id = id;
		this.excludeType = excludeType;
	}
	public AclFuncexcludeVo() {
		super();
	}
	
}
