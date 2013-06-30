/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclPasswordDao;
import com.xuchunchun.abframe.db.entity.AclPassword;
import com.xuchunchun.abframe.db.entity.AclPasswordId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclPasswordDao")
public class AclPasswordDaoImpl extends DefaultDaoImpl<AclPassword> implements AclPasswordDao {

	public AclPassword findByPk(AclPasswordId id) {
		return getHibernateTemplate().get(AclPassword.class, id);
	}
	
	public List<AclPassword> findAll() {
		String hql = "from AclPassword t";
		return getHibernateTemplate().find(hql);
	}
}
