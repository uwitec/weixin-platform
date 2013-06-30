package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.web.vo.AclFuncexcludeVo;


public interface AclFuncexcludeService {
	public void add(AclFuncexclude aclFuncexclude);
	public void delete(List<AclFuncexclude> aclFuncexcludes);
	public void update(AclFuncexclude aclFuncexclude);
	public AclFuncexclude detail(AclFuncexclude aclFuncexclude);
	
	List<AclFuncexclude> querylist(AclFuncexclude aclFuncexclude,
			Map<String, String> clause, int pageNo, int pageSize);

	List<AclFuncexcludeVo> querylistVo(AclFuncexclude aclFuncexclude,
			Map<String, String> clause, int pageNo, int pageSize);
	
	Long queryCount(AclFuncexclude aclFuncexclude, Map<String, String> clause);

}
