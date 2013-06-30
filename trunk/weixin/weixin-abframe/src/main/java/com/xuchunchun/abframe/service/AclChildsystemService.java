package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclChildsystem;


public interface AclChildsystemService {
	public void add(AclChildsystem aclChildsystem);
	public void delete(List<AclChildsystem> aclChildsystems);
	public void update(AclChildsystem aclChildsystem);
	public AclChildsystem detail(AclChildsystem aclChildsystem);
	
	List<AclChildsystem> querylist(AclChildsystem aclChildsystem,
			Map<String, String> clause, int pageNo, int pageSize);

	Long queryCount(AclChildsystem aclChildsystem, Map<String, String> clause);

}
