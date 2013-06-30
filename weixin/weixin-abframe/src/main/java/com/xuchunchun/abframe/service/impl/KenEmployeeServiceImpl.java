package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.KenEmployeeDao;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.service.KenEmployeeService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("kenEmployeeService")
public class KenEmployeeServiceImpl implements KenEmployeeService {
	@Autowired
	private KenEmployeeDao kenEmployeeDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();

	@Override
	public KenEmployee detail(KenEmployee kenEmployee) {
		return this.kenEmployeeDao.findByPk(kenEmployee.getId());
	}

	@Override
	public void delete(List<KenEmployee> kenEmployees) {
		kenEmployeeDao.deleteAll(kenEmployees);
	}

	@Override
	public void update(KenEmployee kenEmployee) {
		this.kenEmployeeDao.update(kenEmployee);
	}

	@Override
	public void add(KenEmployee kenEmployee) {
		kenEmployee.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		this.kenEmployeeDao.save(kenEmployee);
	}

	@Override
	public List<KenEmployee> querylist(KenEmployee kenEmployee,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.kenEmployeeDao.findByTemplateWithPage(kenEmployee, clause,
				pageNo, pageSize);
	}

	@Override
	public Long queryCount(KenEmployee kenEmployee, Map<String, String> clause) {
		return this.kenEmployeeDao.queryCount(kenEmployee, clause);
	}
}
