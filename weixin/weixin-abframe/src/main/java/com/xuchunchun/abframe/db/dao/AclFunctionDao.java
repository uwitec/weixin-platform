/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclFunctionId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclFunctionDao extends DefaultDao<AclFunction> {
	
	public AclFunction findByPk(AclFunctionId id);
	
	public List<AclFunction> findAll();
}
