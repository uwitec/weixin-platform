package com.xuchunchun.weixin.platform.service;

public interface CustomizeMenuService {

	public void createMenu(String menuJson) throws Exception;
	
	public String queryMenu() throws Exception;
	
	public void deleteMenu() throws Exception;
	
	public void updateMenu(String menuJson) throws Exception;

}
