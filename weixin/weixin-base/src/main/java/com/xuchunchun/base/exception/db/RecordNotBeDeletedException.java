/**
 * 文件名：RecordNotBeDeletedException.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：数据库记录删除失败
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-20
 */
package com.xuchunchun.base.exception.db;

import com.xuchunchun.base.exception.DatabaseException;

/**
 * 数据库记录删除失败
 * 数据库记录删除失败
 * @author    xuchunchun
 * @version   1.0  2012-3-20
 */

public class RecordNotBeDeletedException extends DatabaseException {
	private static final long serialVersionUID = 7889726005738832453L;
	private final static String CODE_INDEX = "D";
	
	public RecordNotBeDeletedException(String errorCode, Object ... args) {
		super(CODE_INDEX+errorCode, args);
		// TODO Auto-generated constructor stub
	}

}
