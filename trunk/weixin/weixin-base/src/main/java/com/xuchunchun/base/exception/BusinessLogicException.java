/**
 * 文件名：BusinessLogicException.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：业务逻辑异常
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-20
 */
package com.xuchunchun.base.exception;

/**
 * 业务逻辑异常
 * 所有业务逻辑异常类的父类
 * @author    xuchunchun
 * @version   1.0  2012-3-20
 */

public class BusinessLogicException extends BusinessException {
	private static final long serialVersionUID = 1061843315649897706L;
	//所有业务逻辑异常以WXPB为开头
	private final static String CODE_HERDER="WXPB";
	
	public BusinessLogicException(String errorCode, Object ... args) {
		super(CODE_HERDER+errorCode, args);
	}

	
	
	
}
