package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.KenBankbranch;


public interface KenBankbranchService {
	public void add(KenBankbranch kenBankbranch);
	public void delete(List<KenBankbranch> kenBankbranchs);
	public void update(KenBankbranch kenBankbranch);
	public KenBankbranch detail(KenBankbranch kenBankbranch);
	
	List<KenBankbranch> querylist(KenBankbranch kenBankbranch,
			Map<String, String> clause, int pageNo, int pageSize);

	Long queryCount(KenBankbranch kenBankbranch, Map<String, String> clause);

}
