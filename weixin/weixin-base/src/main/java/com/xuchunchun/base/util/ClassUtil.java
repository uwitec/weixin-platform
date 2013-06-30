package com.xuchunchun.base.util;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;

public class ClassUtil {
	private static Logger logger = Logger.getLogger(ClassUtil.class);
	private static final String GET_STR = "get";
	private static final String ID_STR = "getId";

	public static Object getObj(Object parentObj,String fieldStr) throws Exception{
		Class clazz = parentObj.getClass();
		logger.info("the class name is :"+clazz.getName());//setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if(parentObj instanceof Map){
			Map mapObj = (Map)parentObj;
			if(mapObj.containsKey(fieldStr))return mapObj.get(fieldStr);
			else return mapObj.get(fieldStr.toUpperCase());
		}
		else{
			String funcName = GET_STR+StrUtil.parseFiledName(fieldStr);
			try{
				Method method = clazz.getMethod(funcName, new Class[]{});
				return method.invoke(parentObj, new Object[]{});
			}catch(NoSuchMethodException e){
				Method idMethod = clazz.getMethod(ID_STR, new Class[]{});
				Object _parent = idMethod.invoke(parentObj, new Object[]{});
				Method method = _parent.getClass().getMethod(funcName, new Class[]{});
				return method.invoke(_parent, new Object[]{}); 
			}
		}
	}
}
