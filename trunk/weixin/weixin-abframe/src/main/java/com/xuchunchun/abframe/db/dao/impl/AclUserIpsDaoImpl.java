/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclUserIpsDao;
import com.xuchunchun.abframe.db.entity.AclUserips;
import com.xuchunchun.abframe.db.entity.AclUseripsId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclUserIpsDao")
public class AclUserIpsDaoImpl extends DefaultDaoImpl<AclUserips> implements AclUserIpsDao {

	public AclUserips findByPk(AclUseripsId id) {
		return getHibernateTemplate().get(AclUserips.class, id);
	}
	
	public List<AclUserips> findAll() {
		String hql = "from AclUserips t";
		return getHibernateTemplate().find(hql);
	}
}
