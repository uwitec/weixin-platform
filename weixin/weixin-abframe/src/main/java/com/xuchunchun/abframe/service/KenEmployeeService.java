package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.KenEmployee;


public interface KenEmployeeService {
	public void add(KenEmployee kenEmployee);
	public void delete(List<KenEmployee> kenEmployees);
	public void update(KenEmployee kenEmployee);
	public KenEmployee detail(KenEmployee kenEmployee);
	
	List<KenEmployee> querylist(KenEmployee kenEmployee,
			Map<String, String> clause, int pageNo, int pageSize);

	Long queryCount(KenEmployee kenEmployee, Map<String, String> clause);

}
