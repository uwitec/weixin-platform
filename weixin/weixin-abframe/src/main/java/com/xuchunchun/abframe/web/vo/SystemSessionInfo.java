/**
 * 文件名：SystemSessionInfo.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：系统session信息
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-7-12
 */
package com.xuchunchun.abframe.web.vo;

import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.db.entity.AclSysparameter;

/**
 * 系统session信息类
 * @author    tpf
 * @version   1.0  2012-7-12
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class SystemSessionInfo {

	private AclUser user;
	
	private KenEmployee employee;
	
	private AclSysparameter systemparameter;

	/**
	 * 默认构造方法
	 */
	public SystemSessionInfo() {
		super();
	}

	/**
	 * @return the user
	 */
	public AclUser getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(AclUser user) {
		this.user = user;
	}

	/**
	 * @return the employee
	 */
	public KenEmployee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(KenEmployee employee) {
		this.employee = employee;
	}

	/**
	 * @return the systemparameter
	 */
	public AclSysparameter getSystemparameter() {
		return systemparameter;
	}

	/**
	 * @param systemparameter the systemparameter to set
	 */
	public void setSystemparameter(AclSysparameter systemparameter) {
		this.systemparameter = systemparameter;
	}

}
