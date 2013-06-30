/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclExcludeDao;
import com.xuchunchun.abframe.db.dao.AclFuncExcludeDao;
import com.xuchunchun.abframe.db.entity.AclExclude;
import com.xuchunchun.abframe.db.entity.AclExcludeId;
import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.db.entity.AclFuncexcludeId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclExcludeDao")
public class AclExcludeDaoImpl extends DefaultDaoImpl<AclExclude> implements AclExcludeDao {

	public AclExclude findByPk(AclExcludeId id) {
		return getHibernateTemplate().get(AclExclude.class, id);
	}
	
	public List<AclExclude> findAll() {
		String hql = "from AclExclude t";
		return getHibernateTemplate().find(hql);
	}

}
