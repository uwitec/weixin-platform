/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxPlatformtrxnmemoDao;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemoId;
/**
 * @author tpf
 * 
 */
@Dao("wxPlatformtrxnmemoDao")
public class WxPlatformtrxnmemoDaoImpl extends DefaultDaoImpl<WxPlatformtrxnmemo> implements WxPlatformtrxnmemoDao {

	public WxPlatformtrxnmemo findByPk(WxPlatformtrxnmemoId id) {
		return getHibernateTemplate().get(WxPlatformtrxnmemo.class, id);
	}
	
	public List<WxPlatformtrxnmemo> findAll() {
		String hql = "from WxPlatformtrxnmemo t";
		return getHibernateTemplate().find(hql);
	}

}
