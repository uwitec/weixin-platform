package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import com.xuchunchun.abframe.service.DefaultQueryService;

import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.base.dao.impl.DefaultDaoImpl;

public class DefaultQueryServiceImpl<T> implements DefaultQueryService<T> {
	
	private DefaultDao<T>  defaultDao ;
	
	public DefaultDao<T> getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao<T> defaultDao) {
		this.defaultDao = defaultDao;
	}
	
	@Override
	public List<T> findByTemplate(T t) {
		return defaultDao.findByTemplate(t);
	}
	
	@Override
	public List<T> findByTemplate(T t, Map<String,String> clause) {
		return defaultDao.findByTemplate(t,clause);
	}
	
	@Override
	public List<T> findByTemplateWithPage(T t, Map<String,String> clause, int pageNo, int pageSize) {
		return defaultDao.findByTemplateWithPage(t,clause,pageNo,pageSize);
	}
	@Override
	public long queryCount(T t) {
		return defaultDao.queryCount(t);
	}

	@Override
	public List queryMax(T t, Map<String,String> clause, String ... fieldNames){
		return defaultDao.queryMax(t, clause, fieldNames);
	}
	
	@Override
	public List queryMin(T t, Map<String,String> clause, String ... fieldNames){
		return defaultDao.queryMin(t, clause, fieldNames);
	}
	
	@Override
	public List querySum(T t, Map<String,String> clause, String ... fieldNames){
		return defaultDao.querySum(t, clause, fieldNames);
	}
	
	@Override
	public List queryAvg(T t, Map<String,String> clause, String ... fieldNames){
		return defaultDao.queryAvg(t, clause, fieldNames);
	}
	
	@Override
	public List queryMax(T t, String ... fieldNames){
		return defaultDao.queryMax(t, fieldNames);
	}
	
	@Override
	public List queryMin(T t, String ... fieldNames){
		return defaultDao.queryMin(t, fieldNames);
	}
	
	@Override
	public List querySum(T t, String ... fieldNames){
		return defaultDao.querySum(t, fieldNames);
	}
	
	@Override
	public List queryAvg(T t, String ... fieldNames){
		return defaultDao.queryAvg(t, fieldNames);
	}
	
	@Override
	public List queryBySql(String sql){
		return defaultDao.queryBySql(sql);
	}

	@Override
	public List queryByHql(String hql){
		return defaultDao.queryByHql(hql);
	}
	
	@Override
	public List<T> findByTemplateWithSize(T t, Map<String,String> clause, int size){
		return defaultDao.findByTemplateWithSize(t,clause,size);
	}
	
	@Override
	public List<T> findByTemplateWithOrders(T t, Map<String,String> clause, String[] ... orders) {
		return defaultDao.findByTemplateWithOrders(t,clause,orders);
	}
	
	@Override
	public List<T> findByTemplateWithSizeAndOrders(T t, Map<String,String> clause, int size, String[] ... orders) {
		return defaultDao.findByTemplateWithSizeAndOrders(t,clause,size,orders);
	}
	
	@Override
	public List<T> findByTemplateWithPageAndOrders(T t, Map<String,String> clause, int pageNo,
			int pageSize, String[] ... orders) {
		return defaultDao.findByTemplateWithPageAndOrders(t,clause,pageNo,pageSize,orders);
	}
	
	@Override
	public long queryCount(T t, Map<String, String> clause) {
		return defaultDao.queryCount(t,clause);
	}
	
	@Override
	public List queryWithFunc(T t, Map<String, String> clause, String[] ... fieldNames) {
		return defaultDao.queryWithFunc(t,clause,fieldNames);
	}
	
	

	@Override
	public List<T> findByTemplateWithSize(T t, int size) {
		return defaultDao.findByTemplateWithSize(t,size);
	}

	@Override
	public List<T> findByTemplateWithOrders(T t, String[]... orders) {
		return defaultDao.findByTemplateWithOrders(t,orders);
	}

	@Override
	public List<T> findByTemplateWithSizeAndOrders(T t, int size,
			String[]... orders) {
		return defaultDao.findByTemplateWithSizeAndOrders(t,size,orders);
	}

	@Override
	public List<T> findByTemplateWithPage(T t, int pageNo, int pageSize) {
		return defaultDao.findByTemplateWithPage(t,pageNo,pageSize);
	}

	@Override
	public List<T> findByTemplateWithPageAndOrders(T t, int pageNo,
			int pageSize, String[]... orders) {
		return defaultDao.findByTemplateWithPageAndOrders(t,pageNo,pageSize,orders);
	}

	@Override
	public List queryWithFunc(T t, String[]... fieldNames) {
		return defaultDao.queryWithFunc(t,fieldNames);
	}
}
