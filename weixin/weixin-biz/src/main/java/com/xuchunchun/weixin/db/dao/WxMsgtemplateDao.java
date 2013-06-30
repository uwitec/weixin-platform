/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.weixin.db.dao;

import java.util.List;


import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.weixin.db.entity.WxMsgtemplate;
import com.xuchunchun.weixin.db.entity.WxMsgtemplateId;

/**
 * @author tpf
 * 
 */
public interface WxMsgtemplateDao extends DefaultDao<WxMsgtemplate> {
	
	public WxMsgtemplate findByPk(WxMsgtemplateId id);
	
	public List<WxMsgtemplate> findAll();

}
