/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxCustomerDao;
import com.xuchunchun.weixin.db.entity.WxCustomer;
import com.xuchunchun.weixin.db.entity.WxCustomerId;
/**
 * @author tpf
 * 
 */
@Dao("wxCustomerDao")
public class WxCustomerDaoImpl extends DefaultDaoImpl<WxCustomer> implements WxCustomerDao {

	public WxCustomer findByPk(WxCustomerId id) {
		return getHibernateTemplate().get(WxCustomer.class, id);
	}
	
	public List<WxCustomer> findAll() {
		String hql = "from WxCustomer t";
		return getHibernateTemplate().find(hql);
	}

}
