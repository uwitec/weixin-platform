/**
 * 文件名：RecordNotBeInsertedException.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：数据库记录插入失败
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-20
 */
package com.xuchunchun.base.exception.db;

import com.xuchunchun.base.exception.DatabaseException;

/**
 * 数据库记录插入失败
 * 数据库记录插入失败
 * @author    xuchunchun
 * @version   1.0  2012-3-20
 */

public class RecordNotBeInsertedException extends DatabaseException {
	private static final long serialVersionUID = -2078954903726506380L;
	private final static String CODE_INDEX = "I";
	
	public RecordNotBeInsertedException(String errorCode, Object ... args) {
		super(CODE_INDEX+errorCode, args);
	}

}
