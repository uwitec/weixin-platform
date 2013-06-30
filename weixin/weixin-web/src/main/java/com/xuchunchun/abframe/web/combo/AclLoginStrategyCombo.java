package com.xuchunchun.abframe.web.combo;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclLoginStrategyDao;
import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.base.util.ApplicationContextUtil;

public class AclLoginStrategyCombo {
	private static AclLoginStrategyDao aclLoginStrategyDao = ApplicationContextUtil.getInstanceByBeanId("aclLoginStrategyDao", AclLoginStrategyDao.class);
	public static List<AclLoginstrategy> getloginStrategys(){
		List<AclLoginstrategy> list = aclLoginStrategyDao.findAll();
		return list;
	}
}
