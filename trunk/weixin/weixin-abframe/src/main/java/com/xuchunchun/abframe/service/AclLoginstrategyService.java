package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.web.vo.AclLoginstrategyVo;


public interface AclLoginstrategyService {
	public void add(AclLoginstrategy aclLoginstrategy);
	public void delete(List<AclLoginstrategy> aclLoginstrategys);
	public AclLoginstrategy detail(AclLoginstrategy aclLoginstrategy);
	public void update(AclLoginstrategy aclLoginstrategy);
	
	List<AclLoginstrategy> querylist(AclLoginstrategy aclLoginstrategy,
			Map<String, String> clause, int pageNo, int pageSize);

	List<AclLoginstrategyVo> querylistVo(AclLoginstrategy aclLoginstrategy,
			Map<String, String> clause, int pageNo, int pageSize);
	
	Long queryCount(AclLoginstrategy aclLoginstrategy, Map<String, String> clause);

}
