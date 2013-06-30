/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclRoleMenuDao;
import com.xuchunchun.abframe.db.entity.AclRolemenu;
import com.xuchunchun.abframe.db.entity.AclRolemenuId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclRoleMenuDao")
public class AclRoleMenuDaoImpl extends DefaultDaoImpl<AclRolemenu> implements AclRoleMenuDao {

	public AclRolemenu findByPk(AclRolemenuId id) {
		return getHibernateTemplate().get(AclRolemenu.class, id);
	}
	
	public List<AclRolemenu> findAll() {
		String hql = "from AclRolemenu t";
		return getHibernateTemplate().find(hql);
	}
}
