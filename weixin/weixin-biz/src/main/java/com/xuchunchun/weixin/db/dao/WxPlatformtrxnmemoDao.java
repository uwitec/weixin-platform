/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemoId;

/**
 * @author tpf
 * 
 */
public interface WxPlatformtrxnmemoDao extends DefaultDao<WxPlatformtrxnmemo> {
	
	public WxPlatformtrxnmemo findByPk(WxPlatformtrxnmemoId id);
	
	public List<WxPlatformtrxnmemo> findAll();

}
