package com.xuchunchun.abframe.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.xuchunchun.abframe.db.dao.AclLoginMemoDao;
import com.xuchunchun.abframe.db.dao.AclPasswordDao;
import com.xuchunchun.abframe.db.dao.AclRoleDao;
import com.xuchunchun.abframe.db.dao.AclSysParameterDao;
import com.xuchunchun.abframe.db.dao.AclUserDao;
import com.xuchunchun.abframe.db.dao.AclUserIpsDao;
import com.xuchunchun.abframe.db.dao.AclUserMacsDao;
import com.xuchunchun.abframe.db.dao.AclUserRoleDao;
import com.xuchunchun.abframe.db.dao.KenEmployeeDao;
import com.xuchunchun.abframe.db.entity.AclPassword;
import com.xuchunchun.abframe.db.entity.AclPasswordId;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRoleId;
import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserips;
import com.xuchunchun.abframe.db.entity.AclUseripsId;
import com.xuchunchun.abframe.db.entity.AclUsermacs;
import com.xuchunchun.abframe.db.entity.AclUsermacsId;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.db.entity.AclUserroleId;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.db.entity.KenEmployeeId;
import com.xuchunchun.abframe.service.AclUserService;
import com.xuchunchun.abframe.web.vo.AclUserVo;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclUserService")
public class AclUserServiceImpl implements AclUserService {
	@Autowired
	private AclUserDao aclUserDao;
	@Autowired
	private AclUserRoleDao aclUserRoleDao;
	@Autowired
	private AclRoleDao aclRoleDao;
	@Autowired
	private AclUserMacsDao aclUserMacsDao;
	@Autowired
	private AclUserIpsDao aclUserIpsDao;
	@Autowired
	private AclLoginMemoDao aclLoginMemoDao;
	@Autowired
	private AclSysParameterDao aclSysParameterDao;
	@Autowired
	private AclPasswordDao aclPasswordDao;
	@Autowired
	private KenEmployeeDao kenEmployeeDao;
	//private Long bankorgId = SystemUtil.getDefaultBankorgId();
	
	private final String DEFAULT_PASSWORD = "000000"; 
	private final String LOCKSTATUS = "L";//锁定的状态
	private final String NORMALSTATUS = "N";//正常状态，解锁
	private Date currentDate = new Date();//当前日期
	
	
	//修改密码
	@Override
	public String updatechangePassword(String destPassword,String currentPassword,AclUser aclUser) {
		
		aclUser = aclUserDao.findByPk(aclUser.getUserId());
		//加密新密码 
		String destPasswordSecure =  passwordSecure(destPassword, aclUser.getUserId(), aclUser.getAuthMode());
		//检查当前密码
		String currentPasswordSecure = passwordSecure(currentPassword, aclUser.getUserId(), aclUser.getAuthMode());
		if(!currentPasswordSecure.equalsIgnoreCase(aclUser.getPassword())){
			return "currentWrong";
		}

		//改密码时，检查以往密码
		AclSysparameter aclSysparameter = aclSysParameterDao.findByPk(SystemUtil.getCurrBankorgId());
		if("Y".equalsIgnoreCase(aclSysparameter.getChgPwdOldChk())){
			Long oldCOunt = aclSysparameter.getOldChangeCounts();
			if(oldCOunt>=1){
				//历史密码序号
				String hqlMaxseq = "select max(id.pwdSeq) from AclPassword where id.userId = '"+aclUser.getUserId()+"'";
				List listSeq = aclPasswordDao.queryByHql(hqlMaxseq);
							
				Long seq = new Long(0); //序号
				if(listSeq.get(0)==null||listSeq.size()==0){
					seq = new Long(0);
				}else{
					seq = (Long) listSeq.get(0);
				}
				
				Long bigseq = seq - oldCOunt;
				//查出需要检查的以往密码
				String hqlList = "from aclPassword where userId = '"+aclUser.getUserId()+"' and id.pwdSeq >'"+bigseq +"' order by pwdSeq desc";
				List<AclPassword> listPasswords = aclPasswordDao.queryByHql(hqlList);
				for(AclPassword ps:listPasswords){
					if(destPassword.equals(ps.getPassword()));
					return "historyWrong";
				}
			}
		}
		
		aclUser.setPassword(destPasswordSecure);
		aclUserDao.save(aclUser);
		
		saveAclpassword(destPasswordSecure,aclUser);
		
		return "OK";
	}

	//锁定用户
	@Override
	public void updatelockuser(AclUser aclUser){
		aclUser = aclUserDao.findByPk(aclUser.getUserId());
		aclUser.setUnlocktime(currentDate);
		aclUser.setUserStatus(LOCKSTATUS);
		
		aclUserDao.update(aclUser);
	}
	//解锁用户
	@Override
	public void updateunlockuser(AclUser aclUser){
	//	Timestamp  currentTimestamp = new Timestamp(currentDate.getTime());
	//	aclUser.setUnlocktime(currentTimestamp);
		aclUser = aclUserDao.findByPk(aclUser.getUserId());
		aclUser.setUnlocktime(currentDate);
		aclUser.setUserStatus(NORMALSTATUS);
		aclUserDao.update(aclUser);
	}
	//重置用户密码
	@Override
	public void updateresetpassword(AclUser aclUser){
		
		aclUser = aclUserDao.findByPk(aclUser.getUserId());
		String destPassword = passwordSecure(DEFAULT_PASSWORD,aclUser.getUserId(),aclUser.getAuthMode());
		aclUser.setPassword(destPassword);
		aclUser.setErrCount(new Long(0));
		aclUserDao.update(aclUser);
		
	}
	
	@Override
	public AclUser detailAclUser(AclUser aclUser){
		String userId = aclUser.getUserId();
		return aclUserDao.findByPk(userId);
	}

	//加密方法
	@Override
	public String passwordSecure(String srcPassword,String userId ,String authMode){
		String destPassword = "";
		if("M".equalsIgnoreCase(authMode)){
			destPassword = new Md5PasswordEncoder().encodePassword(srcPassword, userId);
		}else if("S".equalsIgnoreCase(authMode)){
			destPassword = new ShaPasswordEncoder().encodePassword(srcPassword, userId);
		}
		return destPassword; 
	}
	
	/* (non-Javadoc)
	 * @see com.xuchunchun.base.abframe.service.AclUserService#addAclUser(com.xuchunchun.base.abframe.entity.AclUser)
	 */
	@Override
	public void addAclUser(AclUser aclUser) {
		String destPassword = passwordSecure(DEFAULT_PASSWORD,aclUser.getUserId(),aclUser.getAuthMode());
		aclUser.setPassword(destPassword);
		aclUserDao.save(aclUser);
	}

	/* (non-Javadoc)
	 * @see com.xuchunchun.base.abframe.service.AclUserService#deleteAclUser(com.xuchunchun.base.abframe.entity.AclUser)
	 */
	@Override
	public void deleteAclUser(AclUser aclUser) {
		aclUserDao.delete(aclUser);
		
		List<AclUserrole> listAclUserRole = aclUserRoleDao.findAll();
		for(AclUserrole aclUserRole : listAclUserRole){
			if(aclUserRole.getId().getUserId().equals(aclUser.getUserId())){
				aclUserRoleDao.delete(aclUserRole);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.xuchunchun.base.abframe.service.AclUserService#updateAclUser(com.xuchunchun.base.abframe.entity.AclUser)
	 */
	@Override
	public void updateAclUser(AclUser aclUser) {
		AclUser aclUserDetail = aclUserDao.findByPk(aclUser.getUserId());
		aclUserDetail.setEmployeeId(aclUser.getEmployeeId());
		aclUserDetail.setAuthMode(aclUser.getAuthMode());
		aclUserDetail.setUserStatus(aclUser.getUserStatus());
		aclUserDetail.setLoginStrategy(aclUser.getLoginStrategy());
		aclUserDetail.setBankorgId(aclUser.getBankorgId());
		aclUserDao.update(aclUserDetail);
	}

	/* (non-Javadoc)
	 * @see com.xuchunchun.base.abframe.service.AclUserService#findByPK(java.lang.String)
	 */
	@Override
	public AclUser findByPK(String userId) {
		AclUser aclUser = aclUserDao.findByPk(userId);
		return aclUser;
	}

	/* (non-Javadoc)
	 * @see com.xuchunchun.base.abframe.service.AclUserService#querylist(com.xuchunchun.base.abframe.entity.AclUser, java.util.Map, int, int)
	 */
	@Override
	public List<AclUser> querylist(AclUser aclUser, Map<String, String> clause, int pageNo, int pageSize) {
		List<AclUser> userList = aclUserDao.findByTemplateWithPage(aclUser, clause, pageNo, pageSize);
		return userList;
	}
	
	public List<AclUserVo> querylistVo(AclUser aclUser, Map<String, String> clause, int page, int pagesize){
		List<AclUser> userList = aclUserDao.findByTemplateWithPage(aclUser, clause, page, pagesize);
		List<AclUserVo> aclUserVos = new ArrayList<AclUserVo>();
		if(userList != null && userList.size() != 0){
			for(AclUser acl : userList){
				AclUserVo aclVo = new AclUserVo();
				aclVo.setBankorgId(acl.getBankorgId());
				aclVo.setUserId(acl.getUserId());
				String employeeId = acl.getEmployeeId();
				KenEmployeeId kemEmpId = new KenEmployeeId(SystemUtil.getCurrBankorgId(), employeeId);
				KenEmployee kenEmp = kenEmployeeDao.findByPk(kemEmpId);
				if(kenEmp != null){
					aclVo.setEmployeeId(kenEmp.getEmployeeName());
				}else{
					aclVo.setEmployeeId(employeeId);
				}
				String authMedo = acl.getAuthMode();
				if("S".equals(authMedo)){
					aclVo.setAuthMode("SHA");
				}else if("M".equals(authMedo)){
					aclVo.setAuthMode("MD5");
				}else{
					aclVo.setAuthMode(authMedo);
				}
				if("U".equals(acl.getUserStatus())){
					aclVo.setUserStatus("新用户");
				}else if("N".equals(acl.getUserStatus())){
					aclVo.setUserStatus("正常");
				}else if("C".equals(acl.getUserStatus())){
					aclVo.setUserStatus("注销");
				}else if("L".equals(acl.getUserStatus())){
					aclVo.setUserStatus("锁定");
				}
				aclVo.setUnlocktime(acl.getUnlocktime());
				aclVo.setLastlogin(acl.getLastlogin());
				aclVo.setErrCount(acl.getErrCount());
				aclVo.setLoginStrategy(acl.getLoginStrategy());
				
				aclUserVos.add(aclVo);
			}
		}
		return aclUserVos;
	}
	
	/* (non-Javadoc)
	 * @see com.xuchunchun.base.abframe.service.AclUserService#queryCount(com.xuchunchun.base.abframe.entity.AclUser, java.util.Map)
	 */
	@Override
	public Long queryCount(AclUser aclUser, Map<String, String> clause) {
		long count = aclUserDao.queryCount(aclUser, clause);
		return count;
	} 
	
	//角色相关
	@Override
	public List<AclRole> listrole(AclUser aclUser,Map<String, String> clause, int pageNo, int pageSize){
		List<AclRole> listrole = new ArrayList<AclRole>();
		
		AclUserroleId aclUserroleId = new AclUserroleId();
		aclUserroleId.setUserId(aclUser.getUserId());
		AclUserrole aclUserrole = new AclUserrole(aclUserroleId);
		List<AclUserrole> roles = aclUserRoleDao.findByTemplateWithPage(aclUserrole, clause, pageNo, pageSize);
		for(AclUserrole aclUserroleTmp : roles){
			 AclRoleId aclRoleId = new AclRoleId(SystemUtil.getCurrBankorgId(),aclUserroleTmp.getId().getRoldId());
			 AclRole aclRole = aclRoleDao.findByPk(aclRoleId);
			if(aclRole!=null){
				listrole.add(aclRole);
			}
		}
		return listrole;
	}
	@Override
	public Long countrole(AclUser aclUser,Map<String, String> clause){
		AclUserroleId aclUserroleId = new AclUserroleId();
		aclUserroleId.setUserId(aclUser.getUserId());
		AclUserrole aclUserrole = new AclUserrole(aclUserroleId);
		return aclUserRoleDao.queryCount(aclUserrole, clause);
	}
	@Override
	public void addrole(AclUserrole aclUserrole){
		aclUserRoleDao.save(aclUserrole);
	}
	@Override
	public void deleterole(AclUserrole aclUserrole){
		aclUserRoleDao.delete(aclUserrole);
	}

	//用户MAC相关
	@Override
	public List<AclUsermacs> listmac(AclUser aclUser,Map<String, String> clause, int pageNo, int pageSize){
		
		AclUsermacsId aclUserroleId  = new AclUsermacsId();
		aclUserroleId.setUserId(aclUser.getUserId());
		AclUsermacs aclUsermacs = new AclUsermacs(aclUserroleId);
		List<AclUsermacs> listmac  = aclUserMacsDao.findByTemplateWithPage(aclUsermacs, clause, pageNo, pageSize);
		
		return listmac;
	}
	@Override
	public Long countmac(AclUser aclUser,Map<String, String> clause){
		AclUsermacsId aclUserroleId  = new AclUsermacsId();
		aclUserroleId.setUserId(aclUser.getUserId());
		AclUsermacs aclUsermacs = new AclUsermacs(aclUserroleId);
		return aclUserMacsDao.queryCount(aclUsermacs, clause);
	}
	@Override
	public void addmac(AclUsermacs aclUsermacs){
		aclUserMacsDao.save(aclUsermacs);
	}
	@Override
	public void deletemac(AclUsermacs aclUsermacs){
		aclUserMacsDao.delete(aclUsermacs);
	}
	
	//用户IP相关
	@Override
	public List<AclUserips> listip(AclUser aclUser,Map<String, String> clause, int pageNo, int pageSize){
		
		AclUseripsId aclUseripsId  = new AclUseripsId();
		aclUseripsId.setUserId(aclUser.getUserId());
		AclUserips aclUserips = new AclUserips(aclUseripsId);
		List<AclUserips> listip  = aclUserIpsDao.findByTemplateWithPage(aclUserips, clause, pageNo, pageSize);
		
		return listip;
	}
	@Override
	public Long countip(AclUser aclUser,Map<String, String> clause){
		AclUseripsId aclUseripsId  = new AclUseripsId();
		aclUseripsId.setUserId(aclUser.getUserId());
		AclUserips aclUserips = new AclUserips(aclUseripsId);
		return aclUserIpsDao.queryCount(aclUserips, clause);
	}
	@Override
	public void addip(AclUserips aclUserips){
		aclUserIpsDao.save(aclUserips);
	}
	@Override
	public void deleteip(AclUserips aclUserips){
		aclUserIpsDao.delete(aclUserips);
	}
	
	/**
	 * 更新历史密码，记录当前密码
	 * @param newPassword 加密后的密码
	 * @param aclUser
	 */
	@Override
	public void saveAclpassword(String newPassword,AclUser aclUser){
		//历史密码序号
		String hqlMaxseq = "select max(id.pwdSeq) from AclPassword where id.userId = '"+aclUser.getUserId()+"'";
		List listSeq = aclPasswordDao.queryByHql(hqlMaxseq);
					
		Long seq = new Long(0); //序号
		if(listSeq.get(0)==null||listSeq.size()==0){
			seq = new Long(1);
		}else{
			seq = (Long) listSeq.get(0) + 1;
		}
		
		//更新历史
		if(seq>1){
			AclPassword aclPasswordLast = aclPasswordDao.findByPk(new AclPasswordId(aclUser.getUserId(),seq - 1));
			aclPasswordLast.setEndDate(currentDate);
			aclPasswordDao.saveOrUpdate(aclPasswordLast);
		}
		
		//记录当前
		AclPassword aclPasswordCurrent = new AclPassword(new AclPasswordId(aclUser.getUserId(),seq));
		aclPasswordCurrent.setPassword(newPassword);
		aclPasswordCurrent.setStartDate(currentDate);
		aclPasswordDao.save(aclPasswordCurrent);
	}
	//获得系统参数
	@Override
	public AclSysparameter getAclSysparameter(){
		return aclSysParameterDao.findByPk(SystemUtil.getCurrBankorgId());
	}
	
	
}
