/**
 * 文件名：BusinessException.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：业务异常类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：2012-3-19
 */
package com.xuchunchun.base.exception;

import org.apache.log4j.Logger;

import com.xuchunchun.base.util.ErrorCodeUtil;


/**
 * 业务异常类
 * 所有业务异常父类
 * @author    xuchunchun
 * @version   1.0  2012-3-19
 */

public class BusinessException extends Exception {
	private static Logger logger = Logger.getLogger(BusinessException.class);
	private static final long serialVersionUID = 941107479278954933L;
	private String errorCode = null;
	private String errorMessage = null;
	private String errorReason = null;
	private String errorSolution = null;
	
	public BusinessException(String errorCode, Object ... args ){
		this.errorCode = ErrorCodeUtil.formatStr(errorCode);
		logger.debug("the error code is "+errorCode);
		this.errorMessage = ErrorCodeUtil.getErrorMessage(errorCode, args);
		logger.debug("the error message is "+errorMessage);
		this.errorReason = ErrorCodeUtil.getErrorMessageReason(errorCode);
		logger.debug("the error reason is "+errorReason);
		this.errorSolution = ErrorCodeUtil.getErrorMessageSolution(errorCode);
		logger.debug("the error solution is "+errorSolution);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public String getErrorSolution() {
		return errorSolution;
	}

	
	
}
