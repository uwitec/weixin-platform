/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxFriendgroup;
import com.xuchunchun.weixin.db.entity.WxFriendgroupId;

/**
 * @author tpf
 * 
 */
public interface WxFriendgroupDao extends DefaultDao<WxFriendgroup> {
	
	public WxFriendgroup findByPk(WxFriendgroupId id);
	
	public List<WxFriendgroup> findAll();

}
