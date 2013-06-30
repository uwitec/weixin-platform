/**
 * 文件名：HibernateInterceptor.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：Hibernate拦截器
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-3-5
 */
package com.xuchunchun.base.interceptor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.xuchunchun.base.util.SessionUtil;


/**
 * Hibernate拦截器
 * @author    tpf
 * @version   1.0  2012-3-5
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class HibernateInterceptor extends EmptyInterceptor {

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		for ( int i=0; i < propertyNames.length; i++ ) {
        	if ( "modiDate".equals( propertyNames[i] ) ) {
            	state[i] = new Timestamp(new Date().getTime());
            }
            if ( "modiUser".equals( propertyNames[i] ) ) {
            	state[i] = SessionUtil.getCurrUser();
            }
            if ( "version".equals( propertyNames[i] ) ) {
            	if(state[i] == null) {
            		state[i] = 1;
            	} else {
            		state[i] = (Integer)state[i]+1;
            	}
            }
        }
        return true;
	}

	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		for ( int i=0; i < propertyNames.length; i++ ) {
        	if ( "modiDate".equals( propertyNames[i] ) ) {
        		currentState[i] = new Timestamp(new Date().getTime());
            }
            if ( "modiUser".equals( propertyNames[i] ) ) {
            	currentState[i] = SessionUtil.getCurrUser();
            }
            if ( "version".equals( propertyNames[i] ) ) {
            	if(previousState != null) {
	            	if(previousState[i] == null) {
	            		currentState[i] = 1;
	            	} else {
	            		currentState[i] = (Integer)previousState[i]+1;
	            	}
            	} else {
            		currentState[i] = 1;
            	}
            }
        }
        return true;
	}
}
