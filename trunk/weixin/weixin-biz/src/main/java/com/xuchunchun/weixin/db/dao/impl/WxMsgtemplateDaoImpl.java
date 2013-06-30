/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxMsgtemplateDao;
import com.xuchunchun.weixin.db.entity.WxMsgtemplate;
import com.xuchunchun.weixin.db.entity.WxMsgtemplateId;
/**
 * @author tpf
 * 
 */
@Dao("wxMsgtemplateDao")
public class WxMsgtemplateDaoImpl extends DefaultDaoImpl<WxMsgtemplate> implements WxMsgtemplateDao {

	public WxMsgtemplate findByPk(WxMsgtemplateId id) {
		return getHibernateTemplate().get(WxMsgtemplate.class, id);
	}
	
	public List<WxMsgtemplate> findAll() {
		String hql = "from WxMsgtemplate t";
		return getHibernateTemplate().find(hql);
	}

}
