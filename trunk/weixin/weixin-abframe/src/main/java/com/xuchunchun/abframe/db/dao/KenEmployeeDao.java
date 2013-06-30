/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.db.entity.KenEmployeeId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface KenEmployeeDao extends DefaultDao<KenEmployee> {
	
	public KenEmployee findByPk(KenEmployeeId id);
	
	public List<KenEmployee> findAll();
}
