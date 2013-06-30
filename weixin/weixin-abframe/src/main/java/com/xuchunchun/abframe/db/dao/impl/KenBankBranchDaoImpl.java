/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.web.vo.KenBankbranchVo;

import com.xuchunchun.abframe.db.dao.KenBankBranchDao;
import com.xuchunchun.abframe.db.entity.KenBankbranch;
import com.xuchunchun.abframe.db.entity.KenBankbranchId;

import com.xuchunchun.base.annotation.Dao;
import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author zhoubing
 * 
 */
@Dao("kenBankBranchDao")
public class KenBankBranchDaoImpl extends DefaultDaoImpl<KenBankbranch> implements KenBankBranchDao {

	@Override
	public KenBankbranch findByPk(KenBankbranchId id) {
		return getHibernateTemplate().get(KenBankbranch.class, id);
	}
	@Override
	public List<KenBankbranch> findAll() {
		String hql = "from KenBankbranch t";
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<KenBankbranchVo> findVoClasId(String branchGroupId) {
		String hql="select new com.xuchunchun.abframe.web.vo.KenBankbranchVo(t.id.bankorgId,t.id.brBranchId,t.brBranchName) from KenBankbranch t,KenBranchgroupmapping m " +
				"where t.id.bankorgId=m.id.bankorgId and t.id.brBranchId=m.id.brBranchId and m.id.branchGroupId= '"+branchGroupId+"'";
		
		return getHibernateTemplate().find(hql);
	}

	@Override
	public List<KenBankbranch> findByClasId(String branchGroupId) {
		String hql=" from KenBankbranch r where r.id.brBranchId not in"+ 
			     "(select t.id.brBranchId from KenBankbranch t,KenBranchgroupmapping m "+
			       " where t.id.bankorgId=m.id.bankorgId and t.id.brBranchId=m.id.brBranchId and m.id.branchGroupId='"+branchGroupId+"')";
	    return getHibernateTemplate().find(hql);
	}
}
