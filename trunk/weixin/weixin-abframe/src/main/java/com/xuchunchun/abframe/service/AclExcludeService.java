package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclExclude;


public interface AclExcludeService {
	public void add(AclExclude aclExclude);
	public void delete(List<AclExclude> aclExcludes);
	public void update(AclExclude aclExclude);
	public AclExclude detail(AclExclude aclExclude);
	
	List<AclExclude> querylist(AclExclude aclExclude,
			Map<String, String> clause, int pageNo, int pageSize);

	Long queryCount(AclExclude aclExclude, Map<String, String> clause);

}
