/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxCard;
import com.xuchunchun.weixin.db.entity.WxCardId;

/**
 * @author tpf
 * 
 */
public interface WxCardDao extends DefaultDao<WxCard> {
	
	public WxCard findByPk(WxCardId id);
	
	public List<WxCard> findAll();

}
