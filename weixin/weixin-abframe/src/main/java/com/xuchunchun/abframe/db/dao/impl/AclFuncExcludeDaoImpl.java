/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclFuncExcludeDao;
import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.db.entity.AclFuncexcludeId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclFuncExcludeDao")
public class AclFuncExcludeDaoImpl extends DefaultDaoImpl<AclFuncexclude> implements AclFuncExcludeDao {

	public AclFuncexclude findByPk(AclFuncexcludeId id) {
		return getHibernateTemplate().get(AclFuncexclude.class, id);
	}
	
	public List<AclFuncexclude> findAll() {
		String hql = "from AclFuncexclude t";
		return getHibernateTemplate().find(hql);
	}
	/**
	 * 返回特定子系统的用户功能例外表
	 * @param userId
	 * @return
	 */
	@Override
	public List<AclFuncexclude> getUserFuncexcludes(String userId,String systemId){
		String hql = "SELECT DISTINCT f FROM AclFuncexclude f WHERE f.id.functionId IN " +
				"(SELECT r.id.functionId FROM AclRolefunc r  WHERE  r.id.roldId IN " +
				"(SELECT u.id.roldId FROM AclUserrole u WHERE u.id.userId='"+userId+"'))" +
				" AND f.id.functionId IN (SELECT t.id.functionId FROM AclFunction t WHERE t.systemId = '"+systemId+"')";
		return getHibernateTemplate().find(hql);
	}
}
