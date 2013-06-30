package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.service.KenEmployeeService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("kenEmployeeAction")
public class KenEmployeeAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private KenEmployeeService kenEmployeeService;
	
	private List<KenEmployee> kenEmployees;
	
	private KenEmployee kenEmployee;
	
	//add
	public String add(){
		this.kenEmployeeService.add(kenEmployee);
		return SUCCESS;
	}
	//detail
	public String detail() {
		this.kenEmployee = kenEmployeeService.detail(kenEmployee);
		detailTag = "detail";
		return SUCCESS;
	}
	//update
	public String update(){
		this.kenEmployeeService.update(kenEmployee);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.kenEmployeeService.delete(kenEmployees);
		return SUCCESS;
	}
	//list
	public void querylist(){
		if(kenEmployee == null){
			kenEmployee = new KenEmployee();
		}
		kenEmployees = kenEmployeeService.querylist(kenEmployee, clause, page, pagesize);
		totalCount = kenEmployeeService.queryCount(kenEmployee, clause);
//		String datacc1 = "{Rows:[{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}" +
//				",{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}]}";
		returnListJSON(kenEmployees,totalCount);
	}
	
	public List<KenEmployee> getKenEmployees() {
		return kenEmployees;
	}
	public void setKenEmployees(List<KenEmployee> kenEmployees) {
		this.kenEmployees = kenEmployees;
	}
	public KenEmployee getKenEmployee() {
		return kenEmployee;
	}
	public void setKenEmployee(KenEmployee kenEmployee) {
		this.kenEmployee = kenEmployee;
	}

	
}
