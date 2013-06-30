/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxCardDao;
import com.xuchunchun.weixin.db.entity.WxCard;
import com.xuchunchun.weixin.db.entity.WxCardId;
/**
 * @author tpf
 * 
 */
@Dao("wxCardDao")
public class WxCardDaoImpl extends DefaultDaoImpl<WxCard> implements WxCardDao {

	public WxCard findByPk(WxCardId id) {
		return getHibernateTemplate().get(WxCard.class, id);
	}
	
	public List<WxCard> findAll() {
		String hql = "from WxCard t";
		return getHibernateTemplate().find(hql);
	}

}
