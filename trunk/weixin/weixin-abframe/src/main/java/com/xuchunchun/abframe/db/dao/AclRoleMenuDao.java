/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclRolemenu;
import com.xuchunchun.abframe.db.entity.AclRolemenuId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclRoleMenuDao extends DefaultDao<AclRolemenu> {
	
	public AclRolemenu findByPk(AclRolemenuId id);
	
	public List<AclRolemenu> findAll();
}
