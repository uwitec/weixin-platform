/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxCustomer;
import com.xuchunchun.weixin.db.entity.WxCustomerId;

/**
 * @author tpf
 * 
 */
public interface WxCustomerDao extends DefaultDao<WxCustomer> {
	
	public WxCustomer findByPk(WxCustomerId id);
	
	public List<WxCustomer> findAll();

}
