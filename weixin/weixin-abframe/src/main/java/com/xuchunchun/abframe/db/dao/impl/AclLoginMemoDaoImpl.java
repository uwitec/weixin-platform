/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclLoginMemoDao;
import com.xuchunchun.abframe.db.entity.AclLoginmemo;
import com.xuchunchun.abframe.db.entity.AclLoginmemoId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclLoginMemoDao")
public class AclLoginMemoDaoImpl extends DefaultDaoImpl<AclLoginmemo> implements AclLoginMemoDao {

	public AclLoginmemo findByPk(AclLoginmemoId id) {
		return getHibernateTemplate().get(AclLoginmemo.class, id);
	}
	
	public List<AclLoginmemo> findAll() {
		String hql = "from AclLoginmemo t";
		return getHibernateTemplate().find(hql);
	}
}
