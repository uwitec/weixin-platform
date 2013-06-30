package com.xuchunchun.abframe.bean;

import java.util.List;

//非叶子节点
public class ListMenuTreeForm extends ListMenuTreeBase {
	private List<ListMenuTreeBase> children;

	public void setChildren(List<ListMenuTreeBase> children) {
		this.children = children;
	}

	public List<ListMenuTreeBase> getChildren() {
		return children;
	}

}
