/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxFriendgroupDao;
import com.xuchunchun.weixin.db.entity.WxFriendgroup;
import com.xuchunchun.weixin.db.entity.WxFriendgroupId;
/**
 * @author tpf
 * 
 */
@Dao("wxFriendgroupDao")
public class WxFriendgroupDaoImpl extends DefaultDaoImpl<WxFriendgroup> implements WxFriendgroupDao {

	public WxFriendgroup findByPk(WxFriendgroupId id) {
		return getHibernateTemplate().get(WxFriendgroup.class, id);
	}
	
	public List<WxFriendgroup> findAll() {
		String hql = "from WxFriendgroup t";
		return getHibernateTemplate().find(hql);
	}

}
