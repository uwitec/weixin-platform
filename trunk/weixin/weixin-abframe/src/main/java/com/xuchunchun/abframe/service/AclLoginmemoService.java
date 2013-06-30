package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclLoginmemo;


public interface AclLoginmemoService {
	public void add(AclLoginmemo aclLoginmemo);
	public void delete(AclLoginmemo aclLoginmemo);
	public void update(AclLoginmemo aclLoginmemo);
	public AclLoginmemo detail(AclLoginmemo aclLoginmemo);
	
	List<AclLoginmemo> querylist(AclLoginmemo aclLoginmemo,
			Map<String, String> clause, int pageNo, int pageSize);

	Long queryCount(AclLoginmemo aclLoginmemo, Map<String, String> clause);

}
