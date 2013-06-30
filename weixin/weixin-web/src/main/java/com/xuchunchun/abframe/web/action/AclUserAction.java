/**
 * 文件名：AclUserAction.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：系统用户维护action
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-3-30
 */
package com.xuchunchun.abframe.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.web.vo.AclUserVo;

import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserips;
import com.xuchunchun.abframe.db.entity.AclUsermacs;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.service.AclUserService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

/**
 * 系统用户维护action
 * @author    tpf
 * @version   1.0  2012-3-30
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

@Action("aclUserAction")
public class AclUserAction extends DefaultAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private AclUserService aclUserService;
	private AclUser aclUser;
	private List<AclUser> aclUserList;
	private AclUserrole aclUserrole;
	private AclUsermacs aclUsermacs;
	private AclUserips aclUserips;
	private AclSysparameter aclSysparameter;
	private String currentPassword; //当前密码
	private String destPassword; //修改后的密码
	
	private List<AclUserVo> aclUserVos;
	
	public void unlockuser(){
		aclUserService.updateunlockuser(aclUser);
	}
	
	public void lockuser(){
		aclUserService.updatelockuser(aclUser);
	}
	
	public void resetpassword(){
		aclUserService.updateresetpassword(aclUser);
	}

	public String add() {
		aclUserService.addAclUser(aclUser);
		return SUCCESS;
	}
	
	public void delete() {
		aclUserService.deleteAclUser(aclUser);
	}
	
	public String update() {
		aclUserService.updateAclUser(aclUser);
		return SUCCESS;
	}
	
	public String detail() {
		detailTag = "detail";
		HttpServletRequest request = ServletActionContext.getRequest();
		aclUser = aclUserService.findByPK(aclUser.getUserId());
//		request.setAttribute("aclUser", aclUser);
		return SUCCESS;
	}
	
	//list
	public void querylist(){
		aclUserList = aclUserService.querylist(aclUser, clause, page, pagesize);
		//aclUserVos = aclUserService.querylistVo(aclUser, clause, page, pagesize);
		totalCount = aclUserService.queryCount(aclUser, clause);
		returnListJSON(aclUserList,totalCount);
	}
	
	//用于标签页的跳转
	public String tabforword(){
		aclUserService.detailAclUser(aclUser);
		return SUCCESS;
	}
	
	//用户角色
	public void rolelist(){
		List<AclRole> list = aclUserService.listrole(aclUser, clause, page, pagesize);
		totalCount = aclUserService.countrole(aclUser, clause);
		returnListJSON(list, totalCount);
	}
	public void addrole(){
		aclUserService.addrole(aclUserrole);
	}
	public void deleterole(){
		aclUserService.deleterole(aclUserrole);
	}

	//用户mac
	public void maclist(){
		List<AclUsermacs> list = aclUserService.listmac(aclUser, clause, page, pagesize);
		totalCount = aclUserService.countmac(aclUser, clause);
		returnListJSON(list, totalCount);
	}
	public void addmac(){
		aclUserService.addmac(aclUsermacs);
	}
	public void deletemac(){
		aclUserService.deletemac(aclUsermacs);
	}
	
	//用户IP
	public void iplist(){
		List<AclUserips> list = aclUserService.listip(aclUser, clause, page, pagesize);
		totalCount = aclUserService.countip(aclUser, clause);
		returnListJSON(list, totalCount);
	}
	
	public void addip(){
		aclUserService.addip(aclUserips);
	}
	public void deleteip(){
		aclUserService.deleteip(aclUserips);
	}
	
	//打开修改密码的页面
	public String tochangepassword(){
		aclUser = aclUserService.findByPK(aclUser.getUserId());
		setAclSysparameter(aclUserService.getAclSysparameter());
		return SUCCESS;
	}
	//修改密码
	public void changepassword(){
		
		//ok的结果 currentWrong historyWrong OK
		String ok = aclUserService.updatechangePassword(destPassword, currentPassword, aclUser);
		ok = "{ok:'"+ok+"'}"; 
		returnJSON(ok);
	}
	
	public List<AclUser> getAclUserList() {
		return aclUserList;
	}

	public void setAclUserList(List<AclUser> aclUserList) {
		this.aclUserList = aclUserList;
	}

	public AclUserrole getAclUserrole() {
		return aclUserrole;
	}

	public void setAclUserrole(AclUserrole aclUserrole) {
		this.aclUserrole = aclUserrole;
	}

	

	public AclUsermacs getAclUsermacs() {
		return aclUsermacs;
	}

	public void setAclUsermacs(AclUsermacs aclUsermacs) {
		this.aclUsermacs = aclUsermacs;
	}

	public AclUserips getAclUserips() {
		return aclUserips;
	}

	public void setAclUserips(AclUserips aclUserips) {
		this.aclUserips = aclUserips;
	}
	
	

	public void setAclUser(AclUser aclUser) {
		this.aclUser = aclUser;
	}

	public AclUser getAclUser() {
		return aclUser;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getDestPassword() {
		return destPassword;
	}

	public void setDestPassword(String destPassword) {
		this.destPassword = destPassword;
	}

	public void setAclSysparameter(AclSysparameter aclSysparameter) {
		this.aclSysparameter = aclSysparameter;
	}

	public AclSysparameter getAclSysparameter() {
		return aclSysparameter;
	}

	public List<AclUserVo> getAclUserVos() {
		return aclUserVos;
	}

	public void setAclUserVos(List<AclUserVo> aclUserVos) {
		this.aclUserVos = aclUserVos;
	}
	
}
