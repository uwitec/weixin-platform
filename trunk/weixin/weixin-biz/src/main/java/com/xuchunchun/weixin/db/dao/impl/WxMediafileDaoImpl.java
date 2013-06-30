/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxMediafileDao;
import com.xuchunchun.weixin.db.entity.WxMediafile;
import com.xuchunchun.weixin.db.entity.WxMediafileId;
/**
 * @author tpf
 * 
 */
@Dao("wxMediafileDao")
public class WxMediafileDaoImpl extends DefaultDaoImpl<WxMediafile> implements WxMediafileDao {

	public WxMediafile findByPk(WxMediafileId id) {
		return getHibernateTemplate().get(WxMediafile.class, id);
	}
	
	public List<WxMediafile> findAll() {
		String hql = "from WxMediafile t";
		return getHibernateTemplate().find(hql);
	}

}
