/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclMenutreeId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclMenuTreeDao extends DefaultDao<AclMenutree> {
	
	public AclMenutree findByPk(AclMenutreeId id);
	
	public List<AclMenutree> findAll();
}
