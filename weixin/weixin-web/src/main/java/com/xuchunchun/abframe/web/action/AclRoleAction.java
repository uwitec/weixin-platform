package com.xuchunchun.abframe.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.bean.ListMenuTreeBase;
import com.xuchunchun.abframe.bean.UserEmployeeForm;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRolefunc;
import com.xuchunchun.abframe.db.entity.AclRolemenu;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.db.entity.AclUsershortcut;
import com.xuchunchun.abframe.service.AclMenuTreeService;
import com.xuchunchun.abframe.service.AclRoleService;
import com.xuchunchun.abframe.common.AclConstants;
import com.xuchunchun.abframe.web.vo.SystemSessionInfo;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclRoleAction")
public class AclRoleAction extends DefaultAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private AclRoleService aclRoleService;
	@Autowired
	private AclMenuTreeService aclMenuTreeService;
	private AclRole aclRole;
	private AclRolefunc aclRolefunc;
	private AclFunction aclFunction;
	private AclRolemenu aclRolemenu;
	private List<AclRolemenu> listAclRolemenus;
	private List<AclUsershortcut> lsitAclUsershortcuts;
	private AclUser aclUser;
	private AclUserrole aclUserrole;
	
	private List<String> listTest;
	
	public void list(){
		if(aclRole == null){
			aclRole = new AclRole();
		}
		List<AclRole> list = aclRoleService.listAclrole(aclRole, clause, page,  pagesize);
		totalCount = aclRoleService.queryCount(aclRole, clause);
		returnListJSON(list, totalCount);
	}
	
	public void add(){
		aclRoleService.addAclrole(aclRole);
	}
	
	public void delete(){
		aclRoleService.deleteAclrole(aclRole);
	}
	
	public void update(){
		aclRoleService.updateAclrole(aclRole);
	}
	public String detail(){
		detailTag = "detail";
		aclRole = aclRoleService.detail(aclRole);
		return SUCCESS;
	}
	
	//function相关
	public String aclrolefunction(){
		aclRole = aclRoleService.detail(aclRole);
		return SUCCESS;
	}
	//function相关
	public void functionlist(){
		List<AclFunction> list = aclRoleService.listAclrAclRolefuncs(aclRolefunc, clause, page, pagesize);
		totalCount = aclRoleService.countAclrAclRolefuncs(aclRolefunc, clause);
		returnListJSON(list, totalCount);
	}
	//function 相关
	public void functionlistAll(){
		List<AclFunction> list = aclRoleService.listFunctionAll(aclFunction, clause, page, pagesize);
		totalCount = aclRoleService.countFunctionAll(aclFunction, clause);
		returnListJSON(list, totalCount);
	}
	//function相关 删除功能
	public void deletefunc(){
		aclRoleService.deleteAclRolefunc(aclRolefunc);
	}
	//function相关 添加功能
	public void addfunc(){
		aclRoleService.addAclRolefunc(aclRolefunc);
	}
	
	
	//菜单相关
	public String aclrolemenu(){
		aclRole = aclRoleService.detail(aclRole);
		return SUCCESS;
	}
	public void loadroletree(){
		List<ListMenuTreeBase> list = aclMenuTreeService.roleListTreeForm(aclRole.getId().getRoldId());
		 returnJSONLISTOBJ(list);
	}
	public void saverolemenu(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] li= request.getParameterValues("listAclRolemenus");
		List<String> list = listTest;
		aclRoleService.saveAclMenu(aclRole,listAclRolemenus);
	}
	
	//快捷菜单相关
	public void loadUserShortcut() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		SystemSessionInfo sysSessionInfo = (SystemSessionInfo) session.getAttribute(AclConstants.SESSION_SYSINFO);
		List<ListMenuTreeBase> list = aclMenuTreeService.userShortcutListTreeForm(sysSessionInfo.getUser().getUserId());
		returnJSONLISTOBJ(list);
	}
	public void saveAclUsershortcut() {
		HttpServletRequest request = ServletActionContext.getRequest();
		aclMenuTreeService.saveAclUsershortcut(lsitAclUsershortcuts);
	}
	
	//用户相关
	public String aclroleuser(){
		aclRole = aclRoleService.detail(aclRole);
		return SUCCESS;
	}
	public void userList(){
		List<UserEmployeeForm> list = aclRoleService.listAclRoleUsers(aclUserrole, clause, page, pagesize);
		totalCount = aclRoleService.countAclRoleUsers(aclUserrole, clause);
		returnListJSON(list, totalCount);
	}
	public void userListAll(){
		List<UserEmployeeForm> list = aclRoleService.listUserAll(aclUser, clause, page, pagesize);
		totalCount = aclRoleService.countUserAll(aclUser, clause);
		returnListJSON(list, totalCount);
	}
	public void addUser(){
		aclRoleService.addAclUserrole(aclUserrole);
	}
	public void deleteUser(){
		aclRoleService.deleteAclUserrole(aclUserrole);
	}

	public AclRole getAclRole() {
		return aclRole;
	}

	public void setAclRole(AclRole aclRole) {
		this.aclRole = aclRole;
	}

	public void setAclRolefunc(AclRolefunc aclRolefunc) {
		this.aclRolefunc = aclRolefunc;
	}

	public AclRolefunc getAclRolefunc() {
		return aclRolefunc;
	}

	public void setAclFunction(AclFunction aclFunction) {
		this.aclFunction = aclFunction;
	}

	public AclFunction getAclFunction() {
		return aclFunction;
	}

	public void setAclRolemenu(AclRolemenu aclRolemenu) {
		this.aclRolemenu = aclRolemenu;
	}

	public AclRolemenu getAclRolemenu() {
		return aclRolemenu;
	}


	public void setListAclRolemenus(List<AclRolemenu> listAclRolemenus) {
		this.listAclRolemenus = listAclRolemenus;
	}

	public List<AclRolemenu> getListAclRolemenus() {
		return listAclRolemenus;
	}

	public AclUser getAclUser() {
		return aclUser;
	}

	public void setAclUser(AclUser aclUser) {
		this.aclUser = aclUser;
	}

	public AclUserrole getAclUserrole() {
		return aclUserrole;
	}

	public void setAclUserrole(AclUserrole aclUserrole) {
		this.aclUserrole = aclUserrole;
	}

	public List<String> getListTest() {
		return listTest;
	}

	public void setListTest(List<String> listTest) {
		this.listTest = listTest;
	}

	public List<AclUsershortcut> getLsitAclUsershortcuts() {
		return lsitAclUsershortcuts;
	}

	public void setLsitAclUsershortcuts(List<AclUsershortcut> lsitAclUsershortcuts) {
		this.lsitAclUsershortcuts = lsitAclUsershortcuts;
	}
}

