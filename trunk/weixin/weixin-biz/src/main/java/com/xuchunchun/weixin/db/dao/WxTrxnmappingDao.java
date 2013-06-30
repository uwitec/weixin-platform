/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxTrxnmapping;
import com.xuchunchun.weixin.db.entity.WxTrxnmappingId;

/**
 * @author tpf
 * 
 */
public interface WxTrxnmappingDao extends DefaultDao<WxTrxnmapping> {
	
	public WxTrxnmapping findByPk(WxTrxnmappingId id);
	
	public List<WxTrxnmapping> findAll();

}
