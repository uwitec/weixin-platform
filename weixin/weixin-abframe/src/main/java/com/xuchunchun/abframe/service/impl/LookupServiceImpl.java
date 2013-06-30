package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclFunctionDao;
import com.xuchunchun.abframe.db.dao.KenEmployeeDao;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.service.LookupService;

import com.xuchunchun.base.annotation.Business;

@Business("lookupService")
public class LookupServiceImpl implements LookupService{
	@Autowired
	private AclFunctionDao aclFunctionDao;
	@Autowired
	private KenEmployeeDao kenEmployeeDao;
	
	
	//aclFunction
	@Override
	public List<AclFunction> functionList(AclFunction aclFunction,Map<String,String> clause,int pageNo, int pageSize){
		return this.aclFunctionDao.findByTemplateWithPage(aclFunction, clause, pageNo, pageSize);
	}
	@Override
	public Long functionCount(AclFunction aclFunction,Map<String,String> clause) {
		return this.aclFunctionDao.queryCount(aclFunction, clause);
	}
	@Override
	public List<KenEmployee> employeeList(KenEmployee kenEmployee,Map<String, String> clause, int pageNo, int pageSize) {
		return this.kenEmployeeDao.findByTemplateWithPage(kenEmployee, clause, pageNo, pageSize);
	}
	@Override
	public Long employeeCount(KenEmployee kenEmployee,Map<String, String> clause) {
		return this.kenEmployeeDao.queryCount(kenEmployee, clause);
	}
	
}
