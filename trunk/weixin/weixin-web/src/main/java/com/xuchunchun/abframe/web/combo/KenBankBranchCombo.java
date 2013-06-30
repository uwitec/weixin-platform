package com.xuchunchun.abframe.web.combo;

import java.util.List;

import com.xuchunchun.abframe.db.dao.KenBankBranchDao;
import com.xuchunchun.abframe.db.entity.KenBankbranch;
import com.xuchunchun.base.util.ApplicationContextUtil;

public class KenBankBranchCombo {
	private static KenBankBranchDao kenBankBranchDao = ApplicationContextUtil.getInstanceByBeanId("kenBankBranchDao", KenBankBranchDao.class);
	public static List<KenBankbranch> getKenBankbranchs(){
		List<KenBankbranch> list = kenBankBranchDao.findAll();
		return list;
	}

}
