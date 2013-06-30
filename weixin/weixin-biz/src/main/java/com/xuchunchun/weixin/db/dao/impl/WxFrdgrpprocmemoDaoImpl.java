/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxFrdgrpprocmemoDao;
import com.xuchunchun.weixin.db.entity.WxFrdgrpprocmemo;
import com.xuchunchun.weixin.db.entity.WxFrdgrpprocmemoId;
/**
 * @author tpf
 * 
 */
@Dao("wxFrdgrpprocmemoDao")
public class WxFrdgrpprocmemoDaoImpl extends DefaultDaoImpl<WxFrdgrpprocmemo> implements WxFrdgrpprocmemoDao {

	public WxFrdgrpprocmemo findByPk(WxFrdgrpprocmemoId id) {
		return getHibernateTemplate().get(WxFrdgrpprocmemo.class, id);
	}
	
	public List<WxFrdgrpprocmemo> findAll() {
		String hql = "from WxFrdgrpprocmemo t";
		return getHibernateTemplate().find(hql);
	}

}
