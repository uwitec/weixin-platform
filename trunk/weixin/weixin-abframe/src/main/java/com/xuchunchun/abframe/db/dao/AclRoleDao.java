/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRoleId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclRoleDao extends DefaultDao<AclRole> {
	
	public AclRole findByPk(AclRoleId id);
	
	public List<AclRole> findAll();
}
