package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.web.vo.AclLoginstrategyVo;

import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.service.AclLoginstrategyService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclLoginstrategyAction")
public class AclLoginstrategyAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AclLoginstrategyService aclLoginstrategyService;
	
	private List<AclLoginstrategy> aclLoginstrategys;
	
	private AclLoginstrategy aclLoginstrategy;
	
	private List<AclLoginstrategyVo> aclLoginstrategyVos;
	
	//add
	public String add(){
		this.aclLoginstrategyService.add(aclLoginstrategy);
		return SUCCESS;
	}
	//detail
	public String detail() {
		this.aclLoginstrategy = aclLoginstrategyService.detail(aclLoginstrategy);
		detailTag = "detail";
		return SUCCESS;
	}
	//update
	public String update(){
		this.aclLoginstrategyService.update(aclLoginstrategy);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.aclLoginstrategyService.delete(aclLoginstrategys);
		return SUCCESS;
	}
	//list
	public void querylist(){
		if(aclLoginstrategy==null){
			aclLoginstrategy = new AclLoginstrategy();
		}
		//aclLoginstrategys = aclLoginstrategyService.querylist(aclLoginstrategy, clause, page, pagesize);
		aclLoginstrategyVos = aclLoginstrategyService.querylistVo(aclLoginstrategy, clause, page, pagesize);
		totalCount = aclLoginstrategyService.queryCount(aclLoginstrategy, clause);
//		String datacc1 = "{Rows:[{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}" +
//				",{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}]}";
		returnListJSON(aclLoginstrategyVos,totalCount);
	}
	
	//登陆策略AclLoginstrategy得到方法
	public AclLoginstrategy getAclLoginstrategy() {
		return aclLoginstrategy;
	}
	//登陆策略AclLoginstrategy设置方法
	public void setAclLoginstrategy(AclLoginstrategy aclLoginstrategy) {
		this.aclLoginstrategy = aclLoginstrategy;
	}
	//登陆策略aclLoginstrategys得到方法
	public List<AclLoginstrategy> getAclLoginstrategys() {
		return aclLoginstrategys;
	}
	//登陆策略aclLoginstrategys设置方法
	public void setAclLoginstrategys(List<AclLoginstrategy> aclLoginstrategys) {
		this.aclLoginstrategys = aclLoginstrategys;
	}
	
}
