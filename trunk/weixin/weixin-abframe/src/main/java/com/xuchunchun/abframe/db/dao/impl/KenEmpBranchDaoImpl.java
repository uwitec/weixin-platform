/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.KenEmpBranchDao;
import com.xuchunchun.abframe.db.entity.KenEmpbranch;
import com.xuchunchun.abframe.db.entity.KenEmpbranchId;

import com.xuchunchun.base.annotation.Dao;
import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("kenEmpBranchDao")
public class KenEmpBranchDaoImpl extends DefaultDaoImpl<KenEmpbranch> implements KenEmpBranchDao {

	public KenEmpbranch findByPk(KenEmpbranchId id) {
		return getHibernateTemplate().get(KenEmpbranch.class, id);
	}
	
	public List<KenEmpbranch> findAll() {
		String hql = "from KenEmpbranch t";
		return getHibernateTemplate().find(hql);
	}
}
