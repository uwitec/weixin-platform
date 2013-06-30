/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxTrxnDao;
import com.xuchunchun.weixin.db.entity.WxTrxn;
import com.xuchunchun.weixin.db.entity.WxTrxnId;
/**
 * @author tpf
 * 
 */
@Dao("wxTrxnDao")
public class WxTrxnDaoImpl extends DefaultDaoImpl<WxTrxn> implements WxTrxnDao {

	public WxTrxn findByPk(WxTrxnId id) {
		return getHibernateTemplate().get(WxTrxn.class, id);
	}
	
	public List<WxTrxn> findAll() {
		String hql = "from WxTrxn t";
		return getHibernateTemplate().find(hql);
	}

}
