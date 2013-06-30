/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclUserMacsDao;
import com.xuchunchun.abframe.db.entity.AclUsermacs;
import com.xuchunchun.abframe.db.entity.AclUsermacsId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclUserMacsDao")
public class AclUserMacsDaoImpl extends DefaultDaoImpl<AclUsermacs> implements AclUserMacsDao {

	public AclUsermacs findByPk(AclUsermacsId id) {
		return getHibernateTemplate().get(AclUsermacs.class, id);
	}
	
	public List<AclUsermacs> findAll() {
		String hql = "from AclUsermacs t";
		return getHibernateTemplate().find(hql);
	}
}
