package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.service.AclFunctionService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclFunctionAction")
public class AclFunctionAction extends DefaultAction{
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private AclFunctionService aclFunctionService;
	private AclFunction aclFunction;
	private List<AclFunction> aclFunctions;
	
	
	//aclFunction
	public void functionList(){
		if(aclFunction==null){
			aclFunction = new AclFunction();
		}
		List<AclFunction> list = aclFunctionService.listFunctions(aclFunction,clause,page, pagesize);
		totalCount = aclFunctionService.totalFunction(aclFunction, clause);
		returnListJSON(list, totalCount);
	}
	
	public String detail(){
		detailTag = "detail";
		aclFunction = aclFunctionService.detaiAclFunction(aclFunction);
		return SUCCESS;
	}
	
	public String delete(){
		aclFunctionService.deleteAclfunction(aclFunctions);
		return SUCCESS;
	}
	
	public String add(){
		aclFunctionService.addAclfunction(aclFunction);
		return SUCCESS;
	}
	
	public String update(){
		aclFunctionService.updateAclfunction(aclFunction);
		return SUCCESS;
	}
	
	
	public AclFunction getAclFunction() {
		return aclFunction;
	}
	public void setAclFunction(AclFunction aclFunction) {
		this.aclFunction = aclFunction;
	}

	public List<AclFunction> getAclFunctions() {
		return aclFunctions;
	}

	public void setAclFunctions(List<AclFunction> aclFunctions) {
		this.aclFunctions = aclFunctions;
	}
}
