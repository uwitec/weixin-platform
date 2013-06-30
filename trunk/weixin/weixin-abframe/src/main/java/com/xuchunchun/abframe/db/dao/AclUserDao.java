/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclUser;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclUserDao extends DefaultDao<AclUser> {
	
	public AclUser findByPk(String userId);
	
	public List<AclUser> findAll();
}
