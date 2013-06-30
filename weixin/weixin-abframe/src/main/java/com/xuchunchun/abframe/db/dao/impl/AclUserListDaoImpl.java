/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclUserListDao;
import com.xuchunchun.abframe.db.entity.AclUserlist;
import com.xuchunchun.abframe.db.entity.AclUserlistId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclUserListDao")
public class AclUserListDaoImpl extends DefaultDaoImpl<AclUserlist> implements AclUserListDao {

	public AclUserlist findByPk(AclUserlistId id) {
		return getHibernateTemplate().get(AclUserlist.class, id);
	}
	
	public List<AclUserlist> findAll() {
		String hql = "from AclUserlist t";
		return getHibernateTemplate().find(hql);
	}
}
