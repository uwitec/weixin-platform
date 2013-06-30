package com.xuchunchun.abframe.web.action;

import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import com.xuchunchun.abframe.service.ComboService;
import com.xuchunchun.base.action.DefaultAction;

public class AbframeComboAction extends DefaultAction{
	private ComboService comboService;

	//子系统
	public void childrenSystem(){
		returnJSONLISTOBJ(comboService.aclChildsystems());
	}
	
	
	public void setComboService(ComboService comboService) {
		this.comboService = comboService;
	}
	public ComboService getComboService() {
		return comboService;
	}
	
}
