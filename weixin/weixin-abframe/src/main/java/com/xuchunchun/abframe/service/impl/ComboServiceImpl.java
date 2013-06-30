package com.xuchunchun.abframe.service.impl;


import java.util.List;

import com.xuchunchun.abframe.db.dao.AclChildSystemDao;
import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.abframe.service.ComboService;


public class ComboServiceImpl implements ComboService{
	private AclChildSystemDao aclChildSystemDao;

	@Override
	public List<AclChildsystem> aclChildsystems(){
		return aclChildSystemDao.findByTemplate(new AclChildsystem());
	}
	
	public void setAclChildSystemDao(AclChildSystemDao aclChildSystemDao) {
		this.aclChildSystemDao = aclChildSystemDao;
	}
	public AclChildSystemDao getAclChildSystemDao() {
		return aclChildSystemDao;
	}
}
