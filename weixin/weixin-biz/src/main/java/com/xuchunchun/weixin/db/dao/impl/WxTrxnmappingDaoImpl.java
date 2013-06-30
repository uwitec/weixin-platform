/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxTrxnmappingDao;
import com.xuchunchun.weixin.db.entity.WxTrxnmapping;
import com.xuchunchun.weixin.db.entity.WxTrxnmappingId;
/**
 * @author tpf
 * 
 */
@Dao("wxTrxnmappingDao")
public class WxTrxnmappingDaoImpl extends DefaultDaoImpl<WxTrxnmapping> implements WxTrxnmappingDao {

	public WxTrxnmapping findByPk(WxTrxnmappingId id) {
		return getHibernateTemplate().get(WxTrxnmapping.class, id);
	}
	
	public List<WxTrxnmapping> findAll() {
		String hql = "from WxTrxnmapping t";
		return getHibernateTemplate().find(hql);
	}

}
