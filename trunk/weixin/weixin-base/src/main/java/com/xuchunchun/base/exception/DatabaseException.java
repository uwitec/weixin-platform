/**
 * 文件名：DatabaseException.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：数据库操作异常
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-20
 */
package com.xuchunchun.base.exception;

/**
 * 数据库操作异常
 * 所有数据库操作异常类的父类
 * @author    xuchunchun
 * @version   1.0  2012-3-20
 */

public class DatabaseException extends BusinessException {
	private static final long serialVersionUID = 4642087483028103080L;
	//所有数据库操作异常以WXP为开头
	private final static String CODE_HERDER="WXP";
	
	public DatabaseException(String errorCode, Object ... args) {
		super(CODE_HERDER+errorCode, args);
	}

	
	
	
}
