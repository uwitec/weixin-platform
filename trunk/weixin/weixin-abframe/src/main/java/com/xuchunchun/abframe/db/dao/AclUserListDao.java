/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclUserlist;
import com.xuchunchun.abframe.db.entity.AclUserlistId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclUserListDao extends DefaultDao<AclUserlist> {
	
	public AclUserlist findByPk(AclUserlistId id);
	
	public List<AclUserlist> findAll();
}
