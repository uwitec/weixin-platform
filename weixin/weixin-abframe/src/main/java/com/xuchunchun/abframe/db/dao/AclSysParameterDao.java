/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclSysparameter;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclSysParameterDao extends DefaultDao<AclSysparameter> {
	
	public AclSysparameter findByPk(Long bankorgId);
	
	public List<AclSysparameter> findAll();
}
