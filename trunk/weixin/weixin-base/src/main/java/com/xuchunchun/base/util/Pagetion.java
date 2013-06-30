package com.xuchunchun.base.util;

import java.util.List;

public class Pagetion<E> {
	private int curpage;
	private int pagesize;
	private int pages;
	private int total;
	private List<E> list;

	public Pagetion(){
		
	}
	
	public Pagetion(int curpage, int pagesize, int total, List<E> list) {
		super();
		this.curpage = curpage;
		this.pagesize = pagesize;
		this.total = total;
		this.list = list;
		this.pages = (total - 1)/pagesize + 1;
	}

	public int getCurpage() {
		return curpage;
	}

	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

}
