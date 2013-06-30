package com.xuchunchun.abframe.web.combo;

import java.util.List;

import com.xuchunchun.abframe.db.dao.AclRoleDao;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.base.util.ApplicationContextUtil;

public class AclRoleCombo {
	private static AclRoleDao aclRoleDao = ApplicationContextUtil.getInstanceByBeanId("aclRoleDao", AclRoleDao.class);
	public static List<AclRole> getChildSystems(){
		List<AclRole> list = aclRoleDao.findAll();
		return list;
	}
}