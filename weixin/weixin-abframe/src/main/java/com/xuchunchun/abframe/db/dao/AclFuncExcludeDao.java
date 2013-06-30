/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.db.entity.AclFuncexcludeId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclFuncExcludeDao extends DefaultDao<AclFuncexclude> {
	
	public AclFuncexclude findByPk(AclFuncexcludeId id);
	
	public List<AclFuncexclude> findAll();

	/**
	 * 返回特定子系统的用户功能例外表
	 * @param userId
	 * @return
	 */
	List<AclFuncexclude> getUserFuncexcludes(String userId, String systemId);
}
