/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclRoleDao;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRoleId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclRoleDao")
public class AclRoleDaoImpl extends DefaultDaoImpl<AclRole> implements AclRoleDao {

	public AclRole findByPk(AclRoleId id) {
		return getHibernateTemplate().get(AclRole.class, id);
	}
	
	public List<AclRole> findAll() {
		String hql = "from AclRole t";
		return getHibernateTemplate().find(hql);
	}
}
