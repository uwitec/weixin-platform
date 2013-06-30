/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.web.vo.KenBankbranchVo;

import com.xuchunchun.abframe.db.entity.KenBankbranch;
import com.xuchunchun.abframe.db.entity.KenBankbranchId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author zb
 * 
 */
public interface KenBankBranchDao extends DefaultDao<KenBankbranch> {
	
	KenBankbranch findByPk(KenBankbranchId id);
	List<KenBankbranch> findAll();
	List<KenBankbranchVo> findVoClasId(String branchGroupId);
	List<KenBankbranch> findByClasId(String branchGroupId);
}
