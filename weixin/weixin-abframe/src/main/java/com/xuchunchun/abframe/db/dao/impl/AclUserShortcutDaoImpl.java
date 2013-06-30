/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclUserShortcutDao;
import com.xuchunchun.abframe.db.entity.AclUsershortcut;
import com.xuchunchun.abframe.db.entity.AclUsershortcutId;

import com.xuchunchun.base.annotation.Dao;
import com.xuchunchun.base.dao.impl.DefaultDaoImpl;

/**
 * @author tpf
 * 
 */
@Dao("aclUserShortcutDao")
public class AclUserShortcutDaoImpl extends DefaultDaoImpl<AclUsershortcut> implements AclUserShortcutDao {

	public AclUsershortcut findByPk(AclUsershortcutId id) {
		return getHibernateTemplate().get(AclUsershortcut.class, id);
	}
	
	public List<AclUsershortcut> findAll() {
		String hql = "from AclRoleshortcut t";
		return getHibernateTemplate().find(hql);
	}
}
