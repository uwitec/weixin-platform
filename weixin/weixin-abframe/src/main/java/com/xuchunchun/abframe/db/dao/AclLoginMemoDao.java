/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclLoginmemo;
import com.xuchunchun.abframe.db.entity.AclLoginmemoId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclLoginMemoDao extends DefaultDao<AclLoginmemo> {
	
	public AclLoginmemo findByPk(AclLoginmemoId id);
	
	public List<AclLoginmemo> findAll();
}
