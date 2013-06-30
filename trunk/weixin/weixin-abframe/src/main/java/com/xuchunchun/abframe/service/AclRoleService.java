package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.bean.UserEmployeeForm;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRolefunc;
import com.xuchunchun.abframe.db.entity.AclRolemenu;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserrole;


public interface AclRoleService {

	AclRole detail(AclRole aclRole);

	void deleteAclrole(AclRole aclRole);

	void updateAclrole(AclRole aclRole);

	void addAclrole(AclRole aclRole);

	Long queryCount(AclRole aclRole, Map<String, String> clause);

	List<AclRole> listAclrole(AclRole aclRole, Map<String, String> clause, int pageNo, int pageSize);

	void deleteAclRolefunc(AclRolefunc aclRolefunc);

	void addAclRolefunc(AclRolefunc aclRolefunc);

	List<AclFunction> listAclrAclRolefuncs(AclRolefunc aclRolefunc, Map<String,String> clause,int pageNo, int pageSize);
	
	Long countAclrAclRolefuncs(AclRolefunc aclRolefunc,
			Map<String, String> clause);

	List<AclFunction> listFunctionAll(AclFunction aclFunction,
			Map<String, String> clause, int pageNo, int pageSize);

	Long countFunctionAll(AclFunction aclFunction, Map<String, String> clause);

	void saveAclMenu(AclRole aclRole, List<AclRolemenu> list);

	List<UserEmployeeForm> listUserAll(AclUser aclUser,
			Map<String, String> clause, int pageNo, int pageSize);

	Long countUserAll(AclUser aclUser, Map<String, String> clause);

	void deleteAclUserrole(AclUserrole aclUserrole);

	Long countAclRoleUsers(AclUserrole aclUserrole, Map<String, String> clause);

	List<UserEmployeeForm> listAclRoleUsers(AclUserrole aclUserrole,
			Map<String, String> clause, int pageNo, int pageSize);

	void addAclUserrole(AclUserrole aclUserrole);

}
