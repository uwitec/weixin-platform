package com.xuchunchun.abframe.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclChildSystemDao;
import com.xuchunchun.abframe.db.dao.AclFuncExcludeDao;
import com.xuchunchun.abframe.db.dao.AclLoginMemoDao;
import com.xuchunchun.abframe.db.dao.AclLoginStrategyDao;
import com.xuchunchun.abframe.db.dao.AclRoleFuncDao;
import com.xuchunchun.abframe.db.dao.AclSysParameterDao;
import com.xuchunchun.abframe.db.dao.AclUserDao;
import com.xuchunchun.abframe.db.dao.AclUserIpsDao;
import com.xuchunchun.abframe.db.dao.AclUserListDao;
import com.xuchunchun.abframe.db.dao.AclUserMacsDao;
import com.xuchunchun.abframe.db.dao.AclUserRoleDao;
import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.db.entity.AclLoginmemo;
import com.xuchunchun.abframe.db.entity.AclLoginmemoId;
import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.db.entity.AclLoginstrategyId;
import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserips;
import com.xuchunchun.abframe.db.entity.AclUseripsId;
import com.xuchunchun.abframe.db.entity.AclUserlist;
import com.xuchunchun.abframe.db.entity.AclUserlistId;
import com.xuchunchun.abframe.db.entity.AclUsermacs;
import com.xuchunchun.abframe.db.entity.AclUsermacsId;
import com.xuchunchun.abframe.service.AclMenuTreeService;
import com.xuchunchun.abframe.service.AclUserService;
import com.xuchunchun.abframe.service.LoginService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("loginService")
public class LoginServiceImpl implements LoginService{
	//private Long bankorgId = SystemUtil.getDefaultBankorgId();
	@Autowired
	private AclUserDao aclUserDao ;
	@Autowired
	private AclUserListDao aclUserListDao;
	@Autowired
	private AclLoginStrategyDao aclLoginStrategyDao;
	@Autowired
	private AclUserMacsDao aclUserMacsDao;
	@Autowired
	private AclUserIpsDao aclUserIpsDao;
	@Autowired
	private AclLoginMemoDao aclLoginMemoDao;
	@Autowired
	private AclSysParameterDao aclSysParameterDao;
	@Autowired
	private AclChildSystemDao aclChildSystemDao;
	@Autowired
	private AclMenuTreeService aclMenuTreeService;
	@Autowired
	private AclUserService aclUserService;
	@Autowired
	private AclUserRoleDao aclUserRoleDao;
	@Autowired
	private AclRoleFuncDao aclRoleFuncDao;
	@Autowired
	private AclFuncExcludeDao aclFuncExcludeDao;
	
	private AclUser aclUser;
	private String userId; 
	private Date currentDate = new Date();//当前日期
	private String currentMAC ; //当前mac
	private String currentIP;//当前IP
	
	private final String USERNOTFOUND = "userNotFound"; //用户不存在
	private final String PASSWORD = "password"; //密码错误
	private final String MACCHECK = "maccheck"; //MAC地址校验不通过
	private final String IPCHECK = "ipcheck"; //IP地址校验不通过
	private final String NOLIST = "nolist";//不再名单列表或不再时间范围内
	private final String BLIST = "blist" ;//黑名单用户
	private final String CUSER = "cuser" ;//注销用户
	private final String LUSER = "luser" ;//锁定的用户
	private final String UNSTATUS = "unstatus"; //未知状态
	
	private final String WLIST = "wlist" ;//白名单用户
	private final String UUSER = "uuser" ;//新用户，第一次登录
	private final String NUSER = "nuser" ;//正常用户
	
	private final String BUTTON_STRING = "buttonString"; //要设置到子系统session中的功能例外名
	private final String CHILD_USERID = "child_userId"; //传递给子系统的userId变量名
	private final String EMPLOYEEID = "employeeId"; //传递给子系统的employeeId
	
	@Override
	public String login(AclUser aclUser){
		this.aclUser = aclUser;
		if(aclUser == null) {
			return CUSER;
		}
		userId = aclUser.getUserId();
		if(!userCheck()){
			return USERNOTFOUND;
		}
		if(!passwordCheck()){
			updateAcluer();
			addAclLoginmemo(PASSWORD);
			return PASSWORD;
		}
		
		String userStatus = userStatus();
		if(!UUSER.equalsIgnoreCase(userStatus)&&!NUSER.equalsIgnoreCase(userStatus)){
			addAclLoginmemo(userStatus);
			return userStatus;
		}
		
		//用户登录策略
		String loginStrategy = aclUser.getLoginStrategy();
		if(loginStrategy!=null){
			AclLoginstrategyId aclLoginstrategyId = new AclLoginstrategyId();
			aclLoginstrategyId.setBankorgId(SystemUtil.getCurrBankorgId());
			aclLoginstrategyId.setLoginStrategy(loginStrategy);
			AclLoginstrategy aclLoginstrategy = aclLoginStrategyDao.findByPk(aclLoginstrategyId);
		
			boolean wlistInd = false;//白名单指示
			if("Y".equalsIgnoreCase(aclLoginstrategy.getListCheck())){
				String listType = aclLoginstrategy.getListType();
				if(!userList(listType)){
					if("W".equalsIgnoreCase(listType)){
						wlistInd = true;
					}else if("B".equalsIgnoreCase(listType)){
						addAclLoginmemo(BLIST);
						return BLIST;
					}
				}
			}
			if(!wlistInd){
				if("Y".equalsIgnoreCase(aclLoginstrategy.getMacCheck())){
					if(!macCheck()){
						addAclLoginmemo(MACCHECK);
						return MACCHECK;
					}
				}
				if("Y".equalsIgnoreCase(aclLoginstrategy.getIpCheck())){
					if(!ipCheck()){
						addAclLoginmemo(IPCHECK);
						return IPCHECK;
					}
				}
			}
		}
		
		if(UUSER.equalsIgnoreCase(userStatus)){
			updateAcluserErrcount();
			return UUSER;
		}else{
			updateAcluserErrcount();
			return NUSER;
		}
		 
	}
	//用户检查
	private boolean userCheck(){
		AclUser aclUserCheck = new AclUser();
		aclUserCheck.setUserId(aclUser.getUserId());
		
		Long count = aclUserDao.queryCount(aclUserCheck);
		if(count == 1){
			return true;
		}else{
			return false;
		}
	}
	
	//密码检查
	private boolean passwordCheck(){
		if(aclUser.getPassword()==null){
			return false;
		}
		String srcPassword = aclUser.getPassword();
				
		this.aclUser = aclUserDao.findByPk(aclUser.getUserId());
		
		//加密方式
		String authMode = aclUser.getAuthMode();
		
		String destPassword = aclUserService.passwordSecure(srcPassword, aclUser.getUserId(), authMode);
		
		if(destPassword.equals(aclUser.getPassword())){
			return true;
		}else{
			return false;
		}
	
	}
	
	//用户状态
	private String userStatus(){
		String status = aclUser.getUserStatus();
		if("U".equalsIgnoreCase(status)){
			return UUSER;
		}else if("N".equalsIgnoreCase(status)){
			return NUSER;
		}else if("C".equalsIgnoreCase(status)){
			return CUSER;
		}else if("L".equalsIgnoreCase(status)){
			return LUSER;
		}else{
			return UNSTATUS;
		}
	}
	
	//用户名单
	private boolean userList(String listType){
		AclUserlistId aclUserlistId = new AclUserlistId();
		aclUserlistId.setUserId(userId);
		aclUserlistId.setListType(listType);
		AclUserlist aclUserlist = aclUserListDao.findByPk(aclUserlistId);
		if(aclUserlist!=null){
			if(null!=aclUserlist.getStartDate()){
				Date startDate = aclUserlist.getStartDate();
				if(currentDate.compareTo(startDate)<0){
					return true;
				}
			}
			if(null!=aclUserlist.getEndDate()){
				Date endDate = aclUserlist.getStartDate();
				if(currentDate.compareTo(endDate)>0){
					return true;
				}
			}
			return false; //false，说明在客户名单中，且有效
		}
		return true;
	}
	
	//MAC检查
	private boolean macCheck(){
		AclUsermacsId aclUsermacsId = new AclUsermacsId();
		aclUsermacsId.setUserId(userId);
		aclUsermacsId.setMaccode(currentMAC);
		AclUsermacs aclUsermacs = aclUserMacsDao.findByPk(aclUsermacsId);
		if(aclUsermacs==null){
			return false;
		}
		return true;
	}
	
	//IP检查
	private boolean ipCheck(){
		AclUseripsId aclUseripsId = new AclUseripsId();
		aclUseripsId.setUserId(userId);
		aclUseripsId.setIpaddress(currentIP);
		AclUserips aclUserips = aclUserIpsDao.findByPk(aclUseripsId);
		if(aclUserips==null){
			return false;
		}
		return true;
	}
	
	/*
	//用户名单2,都能检查的，暂不用
	private String userList(){
		AclUserlist aclUserlist = new AclUserlist();
		AclUserlistId aclUserlistId = new AclUserlistId();
		aclUserlistId.setUserId(userId);
		List<AclUserlist> list = aclUserListDao.findByTemplate(aclUserlist);
		if(list==null||list.size()==0){
			return NOLIST;
		}
		String whiteInd = "N";
		for(AclUserlist aclUserlistTmp:list){
			if("B".equalsIgnoreCase(aclUserlistTmp.getId().getListType())){
				if(null!=aclUserlistTmp.getStartDate()){
					Date startDate = aclUserlistTmp.getStartDate();
					if(currentDate.compareTo(startDate)<0){
						continue;
					}
				}
				if(null!=aclUserlistTmp.getEndDate()){
					Date endDate = aclUserlistTmp.getStartDate();
					if(currentDate.compareTo(endDate)>0){
						continue;
					}
				}
				return BLIST;
			}else if("W".equalsIgnoreCase(aclUserlistTmp.getId().getListType())){
				if(null!=aclUserlistTmp.getStartDate()){
					Date startDate = aclUserlistTmp.getStartDate();
					if(currentDate.compareTo(startDate)<0){
						continue;
					}
				}
				if(null!=aclUserlistTmp.getEndDate()){
					Date endDate = aclUserlistTmp.getStartDate();
					if(currentDate.compareTo(endDate)>0){
						continue;
					}
				}
				whiteInd = "Y";
			}
		}
		if("Y".equalsIgnoreCase(whiteInd)){
			return WLIST;
		}else{
			return NOLIST;
		}
	}
	*/
	
	/**
	 * 更新系统用户表 Acl_User,密码登录校验次数加1，返回是否超过验证次数,
	 * false超过，true未超过
	 * 超过就将密码锁定
	 */
	private boolean updateAcluer(){
		Long errCount = aclUser.getErrCount();
		if(errCount == null){
			errCount = (long) 1;
		}else{
			errCount = errCount + 1;
		}
		
		aclUser.setErrCount(errCount);
		aclUserDao.update(aclUser);
		
		Long maxErrCount = aclSysParameterDao.findByPk(SystemUtil.getCurrBankorgId()).getMaxErrCount();
		if(maxErrCount==null){
			return true;
		}else{
			if(errCount<maxErrCount){
				return true;
			}else{
				Timestamp  currentTimestamp = new Timestamp(currentDate.getTime());
				aclUser.setUnlocktime(currentTimestamp);
				aclUser.setUserStatus("L"); //设置锁定状态
				aclUserDao.update(aclUser);
				return false;
			}
		}
	}
	
	//记录登录日志
	private void addAclLoginmemo(String longinResult){
		AclLoginmemoId aclLoginmemoId = new AclLoginmemoId();
		aclLoginmemoId.setUserId(aclUser.getUserId());
		AclLoginmemo aclLoginmemo = new AclLoginmemo(aclLoginmemoId);
		
		String hql = "select max(id.loginSeq) as seq from AclLoginmemo where id.userId = '" + aclUser.getUserId()+"'";
		List list = aclLoginMemoDao.queryByHql(hql);
		
		Long loginSeq = new Long(0);
		if(list.get(0)==null||list.size()==0){
			loginSeq = new Long(1);
		}else{
			loginSeq = (Long) list.get(0) + 1;
		}
		aclLoginmemoId.setLoginSeq(loginSeq);//设置序号
		
		aclLoginmemo.setLoginDate(currentDate);
		aclLoginmemo.setAuthMode(aclUser.getAuthMode());
		aclLoginmemo.setUserStatus(aclUser.getUserStatus());
		aclLoginmemo.setLoginStrategy(aclUser.getLoginStrategy());
		
		if(!longinResult.equalsIgnoreCase(UUSER)&&!longinResult.equalsIgnoreCase(NUSER)){
			aclLoginmemo.setLoginResult("F");
			if(longinResult == PASSWORD){
				aclLoginmemo.setReasonType("A");
			}else if(longinResult == BLIST){
				aclLoginmemo.setReasonType("D");
			}else if(longinResult == IPCHECK){
				aclLoginmemo.setReasonType("C");
			}else if(longinResult == MACCHECK){
				aclLoginmemo.setReasonType("B");
			}else if(longinResult == LUSER){
				aclLoginmemo.setReasonType("S");
			}else if(longinResult == CUSER){
				aclLoginmemo.setReasonType("S");
			}else if(longinResult == UNSTATUS){
				aclLoginmemo.setReasonType("S");
			}
			aclLoginmemo.setErrCount(aclUser.getErrCount());
		}else{
			aclLoginmemo.setLoginResult("S");
		}
		
		aclLoginMemoDao.save(aclLoginmemo);
	}
	
	//设置用户登录校验错误次数为0
	private void updateAcluserErrcount(){
		aclUser.setErrCount(new Long(0));
		aclUserDao.update(aclUser);
	}
	
	//首次登录判断是否需要修改密码，不需修改就更改用户状态为N
	@Override
	public boolean firstLogin(AclUser aclUser){
		AclSysparameter aclSysparameter = aclSysParameterDao.findByPk(SystemUtil.getCurrBankorgId());
		if("Y".equalsIgnoreCase(aclSysparameter.getFstPwdChange())){
			return true;
		}else{
			aclUser = aclUserDao.findByPk(aclUser.getUserId());
			aclUser.setUserStatus("N");
			aclUserDao.update(aclUser);
		}
		return false;
	}
	//首次登录密码修改，还需更改用户状态
	@Override
	public void firstChangePassword(String changePasswordString, AclUser aclUser){
		aclUser = aclUserDao.findByPk(aclUser.getUserId());
		//加密
		String destPassword = aclUserService.passwordSecure(changePasswordString,aclUser.getUserId(),aclUser.getAuthMode());
		
		aclUser.setPassword(destPassword);
		aclUser.setUserStatus("N");
		
		aclUserDao.update(aclUser);
	}
	/**
	 * 登录系统的url
	 
	@Override
	public void loginString(String userId, String employeeId){
		List<AclChildsystem> list = aclChildSystemDao.getChildsystemsByUser(userId);
		for (AclChildsystem aclChildsystem : list ) {
			if(aclChildsystem.getLoginUrl()!=null){
				String loginString = aclChildsystem.getSysUrl()+aclChildsystem.getLoginUrl();
				
				System.out.println(loginString);
				
				String queryString = CHILD_USERID+"="+userId;
				if(aclUser.getEmployeeId()!=null){
					queryString+="&"+EMPLOYEEID+"="+employeeId;
				}
				
				//请求子系统的服务器
				remoteServer(loginString, queryString);
				
			}
		}
	}
	
	/**
	 * 请求远程服务器
	public void remoteServer(String urlString,String queryString){
		try{
			// 服务地址
            URL url = new URL(urlString);

            // 设定连接的相关参数
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");

            // 向服务端发送key = value对
            out.write(queryString);
            out.flush();
            out.close();

            // 获取服务端的反馈
            String strLine="";
            String strResponse ="";
            InputStream in =connection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            while((strLine =reader.readLine()) != null)
//            {
//                strResponse +=strLine +"\n";
//            }
//            System.out.print(strResponse);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * 获得特定的功能例外，map
	 * @param userId
	 * @return
	 */
	@Override
	public Map<String, String> getExclude(String userId,String systemId){
		Map<String, String> excludes = new HashMap<String, String>();
		List<AclFuncexclude> aclFuncexcludes = aclFuncExcludeDao.getUserFuncexcludes(userId,systemId);
		for(int i=0; i<aclFuncexcludes.size(); i++){
			AclFuncexclude aclFuncexcludeTmp = aclFuncexcludes.get(i);
			String excludeType = aclFuncexcludeTmp.getExcludeType();
			if(excludeType == null){
				excludeType = "";
			}
			excludes.put(aclFuncexcludeTmp.getId().getExcludeId(), excludeType);
		}
		return excludes;
	}
	
	/**
	 * 获得子系统用户的功能例外,拼接成查询字符串
	 */
	public String getQueryString(String userId,String employeeId,String systemId){
		StringBuffer queryString = new StringBuffer();
		queryString.append("{");
		List<AclFuncexclude> aclFuncexcludes = aclFuncExcludeDao.getUserFuncexcludes(userId,systemId);
		for(int i=0; i<aclFuncexcludes.size(); i++){
			AclFuncexclude aclFuncexcludeTmp = aclFuncexcludes.get(i);
			String liwaiString = "";
			if(i==0){
				liwaiString += "";
			}else{
				liwaiString += ",";
			}
			String excludeType = aclFuncexcludeTmp.getExcludeType();
			if(excludeType == null){
				excludeType = "";
			}
			liwaiString = "'"+aclFuncexcludeTmp.getId().getExcludeId()+"':'"+excludeType+"'";
			queryString.append(liwaiString);
		}
		queryString.append("}");
		String query = "child_userId="+userId+"&employeeId="+employeeId+"&authString="+queryString.toString(); 
		return query;
	}
	
	/**
	 * 登录系统的url
	 */
	@Override
	public List<String> loginStrings(String userId,String employeeId){
		List<String> inStrings = new ArrayList<String>();
		List<AclChildsystem> list = aclChildSystemDao.findAll();
		for (AclChildsystem aclChildsystem : list ) {
			if(aclChildsystem.getLoginUrl()!=null){
				String inString = aclChildsystem.getSysUrl()+aclChildsystem.getLoginUrl();
				String queryString = getQueryString(userId,employeeId,aclChildsystem.getId().getSystemId());
				inString = inString + "?" + queryString;
				inStrings.add(inString);
			}
		}
		return inStrings;
	}
	
	/**
	 * 退出系统的url
	 */
	@Override
	public List<String> logoutStrings(){
		List<String> outStrings = new ArrayList<String>();
		List<AclChildsystem> list = aclChildSystemDao.findAll();
		for (AclChildsystem aclChildsystem : list ) {
			if(aclChildsystem.getLogoutUrl()!=null){
				String outString = aclChildsystem.getSysUrl()+aclChildsystem.getLogoutUrl();
				outStrings.add(outString);
			}
		}
		return outStrings;
	}
	
	
	
}
