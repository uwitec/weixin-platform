package com.xuchunchun.abframe.web.combo;

import java.util.List;

import com.xuchunchun.abframe.db.dao.KenEmployeeDao;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.base.util.ApplicationContextUtil;

public class KenEmployeeCombo {
	private static KenEmployeeDao kenEmployeeDao = ApplicationContextUtil.getInstanceByBeanId("kenEmployeeDao", KenEmployeeDao.class);
	public static List<KenEmployee> getKenEmployees(){
		List<KenEmployee> list = kenEmployeeDao.findAll();
		return list;
	}

}
