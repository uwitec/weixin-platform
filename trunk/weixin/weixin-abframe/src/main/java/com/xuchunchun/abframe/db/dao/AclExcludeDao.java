/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclExclude;
import com.xuchunchun.abframe.db.entity.AclExcludeId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclExcludeDao extends DefaultDao<AclExclude> {
	
	public AclExclude findByPk(AclExcludeId id);
	
	public List<AclExclude> findAll();

}
