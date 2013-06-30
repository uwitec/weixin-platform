/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxTrxn;
import com.xuchunchun.weixin.db.entity.WxTrxnId;

/**
 * @author tpf
 * 
 */
public interface WxTrxnDao extends DefaultDao<WxTrxn> {
	
	public WxTrxn findByPk(WxTrxnId id);
	
	public List<WxTrxn> findAll();

}
