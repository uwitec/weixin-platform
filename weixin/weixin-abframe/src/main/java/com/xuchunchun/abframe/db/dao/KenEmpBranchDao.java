/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.KenEmpbranch;
import com.xuchunchun.abframe.db.entity.KenEmpbranchId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author zhoubing
 * 
 */
public interface KenEmpBranchDao extends DefaultDao<KenEmpbranch> {
	
	public KenEmpbranch findByPk(KenEmpbranchId id);
	
	public List<KenEmpbranch> findAll();
}
