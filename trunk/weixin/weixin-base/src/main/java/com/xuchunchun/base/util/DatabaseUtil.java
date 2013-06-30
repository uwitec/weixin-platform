package com.xuchunchun.base.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.xuchunchun.base.dao.DefaultDao;



public class DatabaseUtil {
	private static Logger logger = Logger.getLogger(DatabaseUtil.class);
	private static String SESSION_FACTORY = "sessionFactory";
	private static SessionFactory sessionFactory = null;
	
	private DatabaseUtil(){
		sessionFactory = ApplicationContextUtil.getInstanceByBeanId(SESSION_FACTORY, SessionFactory.class);
	}
    
	private static DatabaseUtil databaseUtil = null;
	
	public static DatabaseUtil getInstance(){
		if(databaseUtil == null)databaseUtil = new DatabaseUtil();
		return databaseUtil;
	}


	public Object excuteSql(String sql,boolean isMulty) {
		logger.info("sql:"+sql);
		logger.info("isMulty:"+isMulty);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List list =  query.list();
		
		if(isMulty)return list;
		else{
			if(list==null || list.isEmpty())return null;
			else{
				Map _values = (Map)list.get(0);
				Iterator _iterator = _values.values().iterator();
				while (_iterator.hasNext()) {  
					return _iterator.next();
				}					
				return null;	
			}
		}
		
	}
}
