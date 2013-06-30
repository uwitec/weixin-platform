/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclUsermacs;
import com.xuchunchun.abframe.db.entity.AclUsermacsId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclUserMacsDao extends DefaultDao<AclUsermacs> {
	
	public AclUsermacs findByPk(AclUsermacsId id);
	
	public List<AclUsermacs> findAll();
}
