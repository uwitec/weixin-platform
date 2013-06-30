/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclRolefunc;
import com.xuchunchun.abframe.db.entity.AclRolefuncId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclRoleFuncDao extends DefaultDao<AclRolefunc> {
	
	public AclRolefunc findByPk(AclRolefuncId id);
	
	public List<AclRolefunc> findAll();
}
