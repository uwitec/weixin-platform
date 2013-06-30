package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.KenBankBranchDao;
import com.xuchunchun.abframe.db.entity.KenBankbranch;
import com.xuchunchun.abframe.service.KenBankbranchService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("kenBankbranchService")
public class KenBankbranchServiceImpl implements KenBankbranchService {
	@Autowired
	private KenBankBranchDao kenBankBranchDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();

	@Override
	public KenBankbranch detail(KenBankbranch kenBankbranch) {
		return this.kenBankBranchDao.findByPk(kenBankbranch.getId());
	}

	@Override
	public void delete(List<KenBankbranch> kenBankbranchs) {
		this.kenBankBranchDao.deleteAll(kenBankbranchs);
	}

	@Override
	public void update(KenBankbranch kenBankbranch) {
		this.kenBankBranchDao.update(kenBankbranch);
	}

	@Override
	public void add(KenBankbranch kenBankbranch) {
		kenBankbranch.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		this.kenBankBranchDao.save(kenBankbranch);
	}

	@Override
	public List<KenBankbranch> querylist(KenBankbranch kenBankbranch,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.kenBankBranchDao.findByTemplateWithPage(kenBankbranch,
				clause, pageNo, pageSize);
	}

	@Override
	public Long queryCount(KenBankbranch kenBankbranch,
			Map<String, String> clause) {
		return this.kenBankBranchDao.queryCount(kenBankbranch, clause);
	}
}
