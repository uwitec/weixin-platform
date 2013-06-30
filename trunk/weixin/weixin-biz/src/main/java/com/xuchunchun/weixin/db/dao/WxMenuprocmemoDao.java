/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemo;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemoId;

/**
 * @author tpf
 * 
 */
public interface WxMenuprocmemoDao extends DefaultDao<WxMenuprocmemo> {
	
	public WxMenuprocmemo findByPk(WxMenuprocmemoId id);
	
	public List<WxMenuprocmemo> findAll();

}
