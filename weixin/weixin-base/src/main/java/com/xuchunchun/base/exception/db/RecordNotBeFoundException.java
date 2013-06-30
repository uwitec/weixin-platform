/**
 * 文件名：RecordNotFoundException.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：数据库记录找不到
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-20
 */
package com.xuchunchun.base.exception.db;

import com.xuchunchun.base.exception.DatabaseException;

/**
 * 数据库记录找不到
 * 数据库记录找不到
 * @author    xuchunchun
 * @version   1.0  2012-3-20
 */

public class RecordNotBeFoundException extends DatabaseException {
	private static final long serialVersionUID = 2917863676616426739L;
	private final static String CODE_INDEX = "Q";
	
	public RecordNotBeFoundException(String errorCode, Object ... args) {
		super(CODE_INDEX+errorCode, args);
		// TODO Auto-generated constructor stub
	}

}
