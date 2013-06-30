/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.db.entity.AclLoginstrategyId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclLoginStrategyDao extends DefaultDao<AclLoginstrategy> {
	
	public AclLoginstrategy findByPk(AclLoginstrategyId id);
	
	public List<AclLoginstrategy> findAll();
}
