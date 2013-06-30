/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclFunctionDao;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclFunctionId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclFunctionDao")
public class AclFunctionDaoImpl extends DefaultDaoImpl<AclFunction> implements AclFunctionDao {

	public AclFunction findByPk(AclFunctionId id) {
		return getHibernateTemplate().get(AclFunction.class, id);
	}
	
	public List<AclFunction> findAll() {
		String hql = "from AclFunction t";
		return getHibernateTemplate().find(hql);
	}
}
