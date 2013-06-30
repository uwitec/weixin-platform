package com.xuchunchun.abframe.web.combo;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclChildSystemDao;
import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.base.util.ApplicationContextUtil;

public class AclChildSystemCombo {
	private static AclChildSystemDao aclChildSystemDao = ApplicationContextUtil.getInstanceByBeanId("aclChildSystemDao", AclChildSystemDao.class);
	public static List<AclChildsystem> getChildSystems(){
		List<AclChildsystem> list = aclChildSystemDao.findAll();
		return list;
	}
}
