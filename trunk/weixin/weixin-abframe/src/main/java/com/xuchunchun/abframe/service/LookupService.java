package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.KenEmployee;


public interface LookupService {

	List<AclFunction> functionList(AclFunction aclFunction,Map<String, String> clause, int pageNo, int pageSize);
	Long functionCount(AclFunction aclFunction, Map<String, String> clause);

	List<KenEmployee> employeeList(KenEmployee kenEmployee,Map<String, String> clause, int pageNo, int pageSize);
	Long employeeCount(KenEmployee kenEmployee, Map<String, String> clause);
}
