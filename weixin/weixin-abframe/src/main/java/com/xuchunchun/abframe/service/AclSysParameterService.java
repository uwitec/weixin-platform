package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.web.vo.AclSysparameterVo;


public interface AclSysParameterService {

	void add(AclSysparameter aclSysparameter);

	void update(AclSysparameter aclSysparameter);

	AclSysparameter detail(AclSysparameter aclSysparameter);

	void delete(List<AclSysparameter> listSysParameters);

	List<AclSysparameter> querylist(AclSysparameter aclSysparameter,
			Map<String, String> clause, int pageNo, int pageSize);

	List<AclSysparameterVo> querylistVo(AclSysparameter aclSysparameter,
			Map<String, String> clause, int pageNo, int pageSize);
	
	Long queryCount(AclSysparameter aclSysparameter, Map<String, String> clause);
	
	
	/**
	 * 通过银行机构号获取系统级参数信息
	 * 修改日期：2012-6-21
	 * @author: tpf
	 * @param bankorgId
	 * @return
	 */
	public AclSysparameter getSystemParam(long bankorgId);

	/**
	 * 更新系统日期
	 * 修改日期：2012-8-18
	 * @author: tpf
	 */
	public void updateSysDate();

}
