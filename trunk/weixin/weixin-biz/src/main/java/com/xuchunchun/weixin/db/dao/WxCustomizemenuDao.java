/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxCustomizemenu;
import com.xuchunchun.weixin.db.entity.WxCustomizemenuId;

/**
 * @author tpf
 * 
 */
public interface WxCustomizemenuDao extends DefaultDao<WxCustomizemenu> {
	
	public WxCustomizemenu findByPk(WxCustomizemenuId id);
	
	public List<WxCustomizemenu> findAll();
	
	public void deleteAllData(long bankorgId);
	
}
