/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxMenuprocmemoDao;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemo;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemoId;
/**
 * @author tpf
 * 
 */
@Dao("wxMenuprocmemoDao")
public class WxMenuprocmemoDaoImpl extends DefaultDaoImpl<WxMenuprocmemo> implements WxMenuprocmemoDao {

	public WxMenuprocmemo findByPk(WxMenuprocmemoId id) {
		return getHibernateTemplate().get(WxMenuprocmemo.class, id);
	}
	
	public List<WxMenuprocmemo> findAll() {
		String hql = "from WxMenuprocmemo t";
		return getHibernateTemplate().find(hql);
	}

}
