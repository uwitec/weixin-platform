/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclMenuTreeDao;
import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclMenutreeId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclMenuTreeDao")
public class AclMenuTreeDaoImpl extends DefaultDaoImpl<AclMenutree> implements AclMenuTreeDao {

	public AclMenutree findByPk(AclMenutreeId id) {
		return getHibernateTemplate().get(AclMenutree.class, id);
	}
	
	public List<AclMenutree> findAll() {
		String hql = "from AclMenutree t";
		return getHibernateTemplate().find(hql);
	}
}
