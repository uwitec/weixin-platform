package com.xuchunchun.base.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.xuchunchun.base.annotation.Action;


//@Action("forwardAction")
@Controller("forwardAction")
public class ForwardAction extends DefaultAction {

	private static final long serialVersionUID = 1L;

	public String forward(){
		HttpServletRequest request =  ServletActionContext.getRequest();
		String i = request.getParameter("page");
		return SUCCESS;
	}
}
