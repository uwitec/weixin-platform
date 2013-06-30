/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.KenEmployeeDao;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.db.entity.KenEmployeeId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("kenEmployeeDao")
public class KenEmployeeDaoImpl extends DefaultDaoImpl<KenEmployee> implements KenEmployeeDao {

	public KenEmployee findByPk(KenEmployeeId id) {
		return getHibernateTemplate().get(KenEmployee.class, id);
	}
	
	public List<KenEmployee> findAll() {
		String hql = "from KenEmployee t";
		return getHibernateTemplate().find(hql);
	}
}
