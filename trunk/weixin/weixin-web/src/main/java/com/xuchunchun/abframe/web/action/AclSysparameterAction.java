package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.web.vo.AclSysparameterVo;

import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.service.AclSysParameterService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclSysparameterAction")
public class AclSysparameterAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AclSysParameterService aclSysParameterService;
	
	private List<AclSysparameter> listSysParameters;
	
	private AclSysparameter aclSysparameter;
	
	private List<AclSysparameterVo> aclSysparameterVos;
	
	//add
	public String add(){
		aclSysParameterService.add(aclSysparameter);
		return SUCCESS;
	}
	
	//detail
	public String detail() {
		detailTag = "detail";
		aclSysparameter = aclSysParameterService.detail(aclSysparameter);
		return SUCCESS;
	}
	//update
	public String update(){
		aclSysParameterService.update(aclSysparameter);
		return SUCCESS;
	}
	//delete
	public void delete(){
		aclSysParameterService.delete(listSysParameters);
	}
	//list
	public void querylist(){
		if(aclSysparameter==null){
			aclSysparameter = new AclSysparameter();
		}
		//listSysParameters = aclSysParameterService.querylist(aclSysparameter, clause, page, pagesize);
		aclSysparameterVos = aclSysParameterService.querylistVo(aclSysparameter, clause, page, pagesize);
		totalCount = aclSysParameterService.queryCount(aclSysparameter, clause);
		returnListJSON(aclSysparameterVos,totalCount);
	}
	public void setListSysParameters(List<AclSysparameter> listSysParameters) {
		this.listSysParameters = listSysParameters;
	}
	public List<AclSysparameter> getListSysParameters() {
		return listSysParameters;
	}
	
	public void setAclSysparameter(AclSysparameter aclSysparameter) {
		this.aclSysparameter = aclSysparameter;
	}
	
	public AclSysparameter getAclSysparameter() {
		return aclSysparameter;
	}

	public List<AclSysparameterVo> getAclSysparameterVos() {
		return aclSysparameterVos;
	}

	public void setAclSysparameterVos(List<AclSysparameterVo> aclSysparameterVos) {
		this.aclSysparameterVos = aclSysparameterVos;
	}
	
}
