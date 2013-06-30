package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserips;
import com.xuchunchun.abframe.db.entity.AclUsermacs;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.web.vo.AclUserVo;


public interface AclUserService {
	/**
	 * 添加用户
	 * 修改日期：2012-3-30
	 * @author: tpf
	 * @param aclUser
	 */
	public void addAclUser(AclUser aclUser);
	
	/**
	 * 删除用户
	 * 修改日期：2012-3-30
	 * @author: tpf
	 * @param aclUser
	 */
	public void deleteAclUser(AclUser aclUser);
	
	/**
	 * 修改用户
	 * 修改日期：2012-3-30
	 * @author: tpf
	 * @param aclUser
	 */
	public void updateAclUser(AclUser aclUser);
	
	/**
	 * 根据主键查询出的用户
	 * 修改日期：2012-3-30
	 * @author: tpf
	 * @param userId
	 * @return
	 */
	public AclUser findByPK(String userId);
	
	/**
	 * 分布查询
	 * 修改日期：2012-3-30
	 * @author: tpf
	 * @param aclUser
	 * @param clause
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public List<AclUser> querylist(AclUser aclUser, Map<String, String> clause, int page, int pagesize);
	
	public List<AclUserVo> querylistVo(AclUser aclUser, Map<String, String> clause, int page, int pagesize);
	
	/**
	 * 查询总数
	 * 修改日期：2012-3-30
	 * @author: tpf
	 * @param aclUser
	 * @param clause
	 * @return
	 */
	public Long queryCount(AclUser aclUser, Map<String, String> clause);
	AclUser detailAclUser(AclUser aclUser);

	void deleterole(AclUserrole aclUserrole);

	void addrole(AclUserrole aclUserrole);

	Long countrole(AclUser aclUser, Map<String, String> clause);

	List<AclRole> listrole(AclUser aclUser, Map<String, String> clause,
			int pageNo, int pageSize);

	List<AclUsermacs> listmac(AclUser aclUser, Map<String, String> clause,
			int pageNo, int pageSize);

	Long countmac(AclUser aclUser, Map<String, String> clause);

	void addmac(AclUsermacs aclUsermacs);

	void deletemac(AclUsermacs aclUsermacs);

	List<AclUserips> listip(AclUser aclUser, Map<String, String> clause,
			int pageNo, int pageSize);

	Long countip(AclUser aclUser, Map<String, String> clause);

	void addip(AclUserips aclUserips);

	void deleteip(AclUserips aclUserips);

	String passwordSecure(String srcPassword, String userId, String authMode);

	void saveAclpassword(String newPassword, AclUser aclUser);

	AclSysparameter getAclSysparameter();

	String updatechangePassword(String destPassword, String currentPassword,
			AclUser aclUser);

	void updatelockuser(AclUser aclUser);

	void updateunlockuser(AclUser aclUser);

	void updateresetpassword(AclUser aclUser);
}
