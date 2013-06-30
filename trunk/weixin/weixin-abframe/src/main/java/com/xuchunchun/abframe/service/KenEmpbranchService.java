package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.KenEmpbranch;


public interface KenEmpbranchService {
	public void add(KenEmpbranch kenEmpbranch);
	public void delete(List<KenEmpbranch> kenEmpbranchs);
	public void update(KenEmpbranch kenEmpbranch);
	public KenEmpbranch detail(KenEmpbranch kenEmpbranch);
	
	List<KenEmpbranch> querylist(KenEmpbranch kenEmpbranch,
			Map<String, String> clause, int pageNo, int pageSize);

	Long queryCount(KenEmpbranch kenEmpbranch, Map<String, String> clause);

}
