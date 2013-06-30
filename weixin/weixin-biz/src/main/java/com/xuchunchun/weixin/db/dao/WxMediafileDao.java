/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxMediafile;
import com.xuchunchun.weixin.db.entity.WxMediafileId;

/**
 * @author tpf
 * 
 */
public interface WxMediafileDao extends DefaultDao<WxMediafile> {
	
	public WxMediafile findByPk(WxMediafileId id);
	
	public List<WxMediafile> findAll();

}
