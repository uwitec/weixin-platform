/**
 * 文件名：GroovyUtil.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：groovy执行类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 19, 2012
 */
package com.xuchunchun.abframe.util;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;

/**
 * groovy执行类
 * 该类执行groovy脚本，并且返回结果
 * @author    xuchunchun
 * @version   1.0  Feb 19, 2012
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

public class GroovyUtil {
	private static Logger logger = Logger.getLogger(GroovyUtil.class);
	private static GroovyShell shell = null;
	
	public static Object caculate(String syntaxStr,HashMap<String, Object> inputObj){
		logger.info("start to caculate the groovy string.");
		Binding binding = new Binding();
		
		for(Map.Entry<String, Object> entry : inputObj.entrySet()){   
			logger.info("set property for groovy bind:["+entry.getKey()+","+entry.getValue()+"]");
			binding.setProperty(entry.getKey(), entry.getValue());
			
		}
		
		shell = new GroovyShell(Thread.currentThread().getContextClassLoader());
		Script script = shell.parse(syntaxStr);
		script.setBinding(binding);
		Object result=script.run();
		logger.info("end of caculating , the result is "+((result==null)?"":result.toString()));
		return result;
	}
	
}
