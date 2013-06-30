/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclUserips;
import com.xuchunchun.abframe.db.entity.AclUseripsId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclUserIpsDao extends DefaultDao<AclUserips> {
	
	public AclUserips findByPk(AclUseripsId id);
	
	public List<AclUserips> findAll();
}
