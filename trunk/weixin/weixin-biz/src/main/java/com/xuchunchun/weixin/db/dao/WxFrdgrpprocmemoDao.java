/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxFrdgrpprocmemo;
import com.xuchunchun.weixin.db.entity.WxFrdgrpprocmemoId;

/**
 * @author tpf
 * 
 */
public interface WxFrdgrpprocmemoDao extends DefaultDao<WxFrdgrpprocmemo> {
	
	public WxFrdgrpprocmemo findByPk(WxFrdgrpprocmemoId id);
	
	public List<WxFrdgrpprocmemo> findAll();

}
