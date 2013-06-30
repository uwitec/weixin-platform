package com.xuchunchun.base.bean;

import java.util.List;

import org.hibernate.engine.RowSelection;

/**
 * 用于传递到页面的数据
 * @author Dai
 *
 */
public class ListJsonForm {
	public List Rows ; //数据列表
	public Long Total; //总记录数
	
	public ListJsonForm(){}
	
	public ListJsonForm(List Rows, Long Total) {
		this.Rows = Rows;
		this.Total = Total;
	}
	public void setRows(List rows) {
		Rows = rows;
	}
	public List getRows() {
		return Rows;
	}
	public void setTotal(Long total) {
		Total = total;
	}
	public Long getTotal() {
		return Total;
	}
}
