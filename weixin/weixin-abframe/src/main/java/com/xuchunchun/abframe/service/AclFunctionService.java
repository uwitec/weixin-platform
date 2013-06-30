package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclFunction;


public interface AclFunctionService {

	List<AclFunction> listFunctions(AclFunction aclFunction,
			Map<String, String> clause, int pageNo, int pageSize);

	Long totalFunction(AclFunction aclFunction, Map<String, String> clause);

	AclFunction detaiAclFunction(AclFunction aclFunction);

	void updateAclfunction(AclFunction aclFunction);

	void deleteAclfunction(List<AclFunction> aclFunctions);

	void addAclfunction(AclFunction aclFunction);

}
