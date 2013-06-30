package com.xuchunchun.abframe.service;

import java.util.List;
import java.util.Map;

public interface DefaultQueryService<T> {

	List<T> findByTemplate(T t, Map<String, String> clause);

	List<T> findByTemplateWithSize(T t, Map<String, String> clause, int size);

	List<T> findByTemplateWithOrders(T t, Map<String, String> clause,
			String[][] orders);

	List<T> findByTemplateWithSizeAndOrders(T t, Map<String, String> clause,
			int size, String[][] orders);

	List<T> findByTemplateWithPage(T t, Map<String, String> clause, int pageNo,
			int pageSize);

	List<T> findByTemplateWithPageAndOrders(T t, Map<String, String> clause,
			int pageNo, int pageSize, String[][] orders);

	long queryCount(T t, Map<String, String> clause);

	List queryWithFunc(T t, Map<String, String> clause, String[][] fieldNames);

	List<T> findByTemplate(T t);

	List<T> findByTemplateWithOrders(T t, String[][] orders);

	List<T> findByTemplateWithSize(T t, int size);

	List<T> findByTemplateWithSizeAndOrders(T t, int size, String[][] orders);

	List<T> findByTemplateWithPage(T t, int pageNo, int pageSize);

	List<T> findByTemplateWithPageAndOrders(T t, int pageNo, int pageSize,
			String[][] orders);

	long queryCount(T t);

	List queryWithFunc(T t, String[][] fieldNames);

	List queryMax(T t, Map<String, String> clause, String[] fieldNames);

	List queryMin(T t, Map<String, String> clause, String[] fieldNames);

	List querySum(T t, Map<String, String> clause, String[] fieldNames);

	List queryAvg(T t, Map<String, String> clause, String[] fieldNames);

	List queryMax(T t, String[] fieldNames);

	List queryMin(T t, String[] fieldNames);

	List querySum(T t, String[] fieldNames);

	List queryAvg(T t, String[] fieldNames);

	List queryBySql(String sql);

	List queryByHql(String hql);
	
}
