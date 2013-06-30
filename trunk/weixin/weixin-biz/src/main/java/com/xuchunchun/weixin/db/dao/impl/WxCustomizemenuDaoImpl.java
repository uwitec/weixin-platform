/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao.impl;

import java.util.List;



import com.xuchunchun.base.annotation.Dao;

import com.xuchunchun.base.dao.impl.DefaultDaoImpl;
import com.xuchunchun.weixin.db.dao.WxCustomizemenuDao;
import com.xuchunchun.weixin.db.entity.WxCustomizemenu;
import com.xuchunchun.weixin.db.entity.WxCustomizemenuId;
/**
 * @author tpf
 * 
 */
@Dao("wxCustomizemenuDao")
public class WxCustomizemenuDaoImpl extends DefaultDaoImpl<WxCustomizemenu> implements WxCustomizemenuDao {

	public WxCustomizemenu findByPk(WxCustomizemenuId id) {
		return getHibernateTemplate().get(WxCustomizemenu.class, id);
	}
	
	public List<WxCustomizemenu> findAll() {
		String hql = "from WxCustomizemenu t";
		return getHibernateTemplate().find(hql);
	}
	
	public void deleteAllData(long bankorgId){
		WxCustomizemenuId id = new WxCustomizemenuId();
		id.setBankorgId(bankorgId);
		WxCustomizemenu wxCustomizemenu = new WxCustomizemenu();
		wxCustomizemenu.setId(id);
		getHibernateTemplate().delete(wxCustomizemenu);
	}


}
