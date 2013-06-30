/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclSysParameterDao;
import com.xuchunchun.abframe.db.entity.AclSysparameter;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclSysParameterDao")
public class AclSysParameterDaoImpl extends DefaultDaoImpl<AclSysparameter> implements AclSysParameterDao {

	public AclSysparameter findByPk(Long bankorgId) {
		return getHibernateTemplate().get(AclSysparameter.class, bankorgId);
	}
	
	public List<AclSysparameter> findAll() {
		String hql = "from AclSysparameter t";
		return getHibernateTemplate().find(hql);
	}

}
