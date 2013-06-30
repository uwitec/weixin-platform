/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao.impl;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclChildSystemDao;
import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.abframe.db.entity.AclChildsystemId;

import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
/**
 * @author tpf
 * 
 */
@Dao("aclChildSystemDao")
public class AclChildSystemDaoImpl extends DefaultDaoImpl<AclChildsystem> implements AclChildSystemDao {

	public AclChildsystem findByPk(AclChildsystemId id) {
		return getHibernateTemplate().get(AclChildsystem.class, id);
	}
	
	public List<AclChildsystem> findAll() {
		String hql = "from AclChildsystem t";
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据用户ID获得，用户拥有的子系统
	 * @return
	 */
	@Override
	public List<AclChildsystem> getChildsystemsByUser(String userId){
//		String hql = "SELECT * FROM acl_childsystem s WHERE s.system_id IN ( " +
//				"SELECT f.system_id FROM acl_function f WHERE f.function_id IN ( " +
//				"SELECT m.function_id FROM acl_menutree m WHERE m.node_id IN ( " +
//				"SELECT c.node_id FROM acl_rolemenu c WHERE c.rold_id IN ( " +
//				"SELECT u.rold_id FROM acl_userrole u WHERE u.user_id='"+userId+"' )))) ";
		String hql = "from AclChildsystem t";
		return getHibernateTemplate().find(hql);
	}
}
