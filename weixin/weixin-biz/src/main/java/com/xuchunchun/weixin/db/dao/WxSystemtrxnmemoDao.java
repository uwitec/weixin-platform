/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemoId;

/**
 * @author tpf
 * 
 */
public interface WxSystemtrxnmemoDao extends DefaultDao<WxSystemtrxnmemo> {
	
	public WxSystemtrxnmemo findByPk(WxSystemtrxnmemoId id);
	
	public List<WxSystemtrxnmemo> findAll();

}
