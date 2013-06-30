/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.db.entity.AclUserroleId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclUserRoleDao extends DefaultDao<AclUserrole> {
	
	public AclUserrole findByPk(AclUserroleId id);
	
	public List<AclUserrole> findAll();
}
