package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.bean.ListMenuTreeForm;
import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclUser;


public interface LoginService {

	String login(AclUser aclUser);

	boolean firstLogin(AclUser aclUser);

	void firstChangePassword(String changePasswordString, AclUser aclUser);

	List<String> logoutStrings();

	List<String> loginStrings(String userId, String employeeId);

	Map<String, String> getExclude(String userId, String systemId);

}
