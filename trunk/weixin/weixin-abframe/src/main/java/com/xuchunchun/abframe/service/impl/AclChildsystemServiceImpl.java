package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclChildSystemDao;
import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.abframe.service.AclChildsystemService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclChildsystemService")
public class AclChildsystemServiceImpl implements AclChildsystemService {
	@Autowired
	private AclChildSystemDao aclChildSystemDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();

	@Override
	public AclChildsystem detail(AclChildsystem aclChildsystem) {
		return this.aclChildSystemDao.findByPk(aclChildsystem.getId());
	}

	@Override
	public void delete(List<AclChildsystem> aclChildsystems) {
		this.aclChildSystemDao.deleteAll(aclChildsystems);
	}

	@Override
	public void update(AclChildsystem aclChildsystem) {
		this.aclChildSystemDao.update(aclChildsystem);
	}

	@Override
	public void add(AclChildsystem aclChildsystem) {
		aclChildsystem.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		this.aclChildSystemDao.save(aclChildsystem);
	}

	@Override
	public List<AclChildsystem> querylist(AclChildsystem aclChildsystem,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.aclChildSystemDao.findByTemplateWithPage(aclChildsystem,
				clause, pageNo, pageSize);
	}

	@Override
	public Long queryCount(AclChildsystem aclChildsystem,
			Map<String, String> clause) {
		return this.aclChildSystemDao.queryCount(aclChildsystem, clause);
	}
}
