package com.xuchunchun.base.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;
import com.xuchunchun.base.bean.ListJsonForm;
import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.base.util.JsonDateValueProcessor;

public class DefaultAction extends ActionSupport{
	private Logger defaultActionlogger = Logger.getLogger(DefaultAction.class);
	private static final long serialVersionUID = 1L;
	//
	public PrintWriter responseStream ;
	
	//是否详细信息指示
	protected String detailTag;
	//页码数
	protected int page;
	//每页记录数
	protected int pagesize;
	//总记录数
	protected Long totalCount;
	//用于查询和排序
	protected Map<String,String> clause;

	public Map<String, String> getClause() {
		return clause;
	}
	public void setClause(Map<String, String> clause) {
		this.clause = clause;
	}
	
	public void setDetailTag(String detailTag) {
		this.detailTag = detailTag;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	//每页条数
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	public void returnJSON(Object object){
		try {
			HttpServletResponse response =  ServletActionContext.getResponse();
			response.setCharacterEncoding(BaseConstants.BASE_CHARSET);
			responseStream = response.getWriter();
			responseStream.println(parseJSON(object));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//Rows:列表，Total:总记录数
	public void returnListJSON(List Rows, Long Total){
		ListJsonForm listJsonForm = new ListJsonForm(Rows,Total);
		try {
			HttpServletResponse response =  ServletActionContext.getResponse();
			response.setCharacterEncoding(BaseConstants.BASE_CHARSET);
			responseStream = response.getWriter();
			responseStream.println(parseJSON(listJsonForm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 直接写回字符串
	 * 修改日期：2012-7-29
	 * @author: tpf
	 * @param str
	 */
	public void returnString(String str) {
		try {
			HttpServletResponse response =  ServletActionContext.getResponse();
			response.setCharacterEncoding(BaseConstants.BASE_CHARSET);
			responseStream = response.getWriter();
			responseStream.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void returnJSONLISTOBJ(List list){
		try {
			HttpServletResponse response =  ServletActionContext.getResponse();
			response.setCharacterEncoding(BaseConstants.BASE_CHARSET);
			responseStream = response.getWriter();
			responseStream.println(parseJSONList(list));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//包装成JSON对象
	public String parseJSON(Object obj){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		JSONObject jo = JSONObject.fromObject(obj, jsonConfig);
		defaultActionlogger.debug(obj.getClass()+" parseResult:"+jo.toString());
		return jo.toString();
	}
	//将list包装成JSON数组
	public String parseJSONList(List list){
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			jsonArray.add(i,list.get(i));
		}
		return jsonArray.toString();
	}
	
	public String getDetailTag(){
		return detailTag;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

}
