/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclUsershortcut;
import com.xuchunchun.abframe.db.entity.AclUsershortcutId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclUserShortcutDao extends DefaultDao<AclUsershortcut> {
	
	public AclUsershortcut findByPk(AclUsershortcutId id);
	
	public List<AclUsershortcut> findAll();
}
