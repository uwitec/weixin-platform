/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclPassword;
import com.xuchunchun.abframe.db.entity.AclPasswordId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclPasswordDao extends DefaultDao<AclPassword> {
	
	public AclPassword findByPk(AclPasswordId id);
	
	public List<AclPassword> findAll();
}
