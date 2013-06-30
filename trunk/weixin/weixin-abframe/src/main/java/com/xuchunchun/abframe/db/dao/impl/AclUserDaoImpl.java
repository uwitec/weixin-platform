/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclUserDao;
import com.xuchunchun.abframe.db.entity.AclUser;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclUserDao")
public class AclUserDaoImpl extends DefaultDaoImpl<AclUser> implements AclUserDao {

	public AclUser findByPk(String userId) {
		return getHibernateTemplate().get(AclUser.class, userId);
	}
	
	public List<AclUser> findAll() {
		String hql = "from AclUser t";
		return getHibernateTemplate().find(hql);
	}
}
