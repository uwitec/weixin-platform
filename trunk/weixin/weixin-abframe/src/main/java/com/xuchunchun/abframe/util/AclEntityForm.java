package com.xuchunchun.abframe.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import sun.print.resources.serviceui;

/**
 * 将实体的数据转成form的
 * @author Dai
 * @param <T>
 *
 */
public class AclEntityForm<T,S> {
	private Class<T> clazz;
	
	
	public S transaction(T t,S s) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
//		String entityTypeName = t.getClass().getName();
//		String formName = entityTypeName+"Form";
//		Class<S> clazzForm = (Class<S>) Class.forName(formName);
		
		Constructor<S> constructor = (Constructor<S>) s.getClass().getConstructor(t.getClass()); 
		return constructor.newInstance(t);
	}
	
}
