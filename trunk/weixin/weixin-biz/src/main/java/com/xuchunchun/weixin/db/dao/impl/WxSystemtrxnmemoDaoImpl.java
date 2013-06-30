/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxSystemtrxnmemoDao;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemoId;
/**
 * @author tpf
 * 
 */
@Dao("wxSystemtrxnmemoDao")
public class WxSystemtrxnmemoDaoImpl extends DefaultDaoImpl<WxSystemtrxnmemo> implements WxSystemtrxnmemoDao {

	public WxSystemtrxnmemo findByPk(WxSystemtrxnmemoId id) {
		return getHibernateTemplate().get(WxSystemtrxnmemo.class, id);
	}
	
	public List<WxSystemtrxnmemo> findAll() {
		String hql = "from WxSystemtrxnmemo t";
		return getHibernateTemplate().find(hql);
	}

}
