package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.service.LookupService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("abframeLookup")
public class AbframeLookupAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LookupService lookupService ;
	
	//aclFunction
	private AclFunction aclFunction;
	public void functionList(){
		if(aclFunction==null){
			aclFunction = new AclFunction();
		}
		List<AclFunction> list = lookupService.functionList(aclFunction,clause,page, pagesize);
		totalCount = lookupService.functionCount(aclFunction, clause);
		returnListJSON(list, totalCount);
	}
	public void setAclFunction(AclFunction aclFunction) {this.aclFunction = aclFunction;}


	//kenEmployee
	private KenEmployee kenEmployee;
	public void employeeList(){
		if(kenEmployee==null){
			kenEmployee = new KenEmployee();
		}
		List<KenEmployee> list = lookupService.employeeList(kenEmployee,clause,page, pagesize);
		totalCount = lookupService.employeeCount(kenEmployee, clause);
		returnListJSON(list, totalCount);
	}
	public void setKenEmployee(KenEmployee kenEmployee) {
		this.kenEmployee = kenEmployee;
	}
	public KenEmployee getKenEmployee() {
		return kenEmployee;
	}
}
	
