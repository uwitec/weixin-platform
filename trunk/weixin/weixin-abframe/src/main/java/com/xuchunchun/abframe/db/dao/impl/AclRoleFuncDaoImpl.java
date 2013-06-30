/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclRoleFuncDao;
import com.xuchunchun.abframe.db.entity.AclRolefunc;
import com.xuchunchun.abframe.db.entity.AclRolefuncId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclRoleFuncDao")
public class AclRoleFuncDaoImpl extends DefaultDaoImpl<AclRolefunc> implements AclRoleFuncDao {

	public AclRolefunc findByPk(AclRolefuncId id) {
		return getHibernateTemplate().get(AclRolefunc.class, id);
	}
	
	public List<AclRolefunc> findAll() {
		String hql = "from AclRolefunc t";
		return getHibernateTemplate().find(hql);
	}
}
