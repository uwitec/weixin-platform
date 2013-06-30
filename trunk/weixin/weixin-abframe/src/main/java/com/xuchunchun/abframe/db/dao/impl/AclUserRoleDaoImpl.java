/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclUserRoleDao;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.db.entity.AclUserroleId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclUserRoleDao")
public class AclUserRoleDaoImpl extends DefaultDaoImpl<AclUserrole> implements AclUserRoleDao {

	public AclUserrole findByPk(AclUserroleId id) {
		return getHibernateTemplate().get(AclUserrole.class, id);
	}
	
	public List<AclUserrole> findAll() {
		String hql = "from AclUserrole t";
		return getHibernateTemplate().find(hql);
	}
}
