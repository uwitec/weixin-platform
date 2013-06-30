/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclLoginStrategyDao;
import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.db.entity.AclLoginstrategyId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclLoginStrategyDao")
public class AclLoginStrategyDaoImpl extends DefaultDaoImpl<AclLoginstrategy> implements AclLoginStrategyDao {

	public AclLoginstrategy findByPk(AclLoginstrategyId id) {
		return getHibernateTemplate().get(AclLoginstrategy.class, id);
	}
	
	public List<AclLoginstrategy> findAll() {
		String hql = "from AclLoginstrategy t";
		return getHibernateTemplate().find(hql);
	}
}
