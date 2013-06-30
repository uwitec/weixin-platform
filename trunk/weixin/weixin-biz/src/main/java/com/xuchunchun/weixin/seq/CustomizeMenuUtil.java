package com.xuchunchun.weixin.seq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.xuchunchun.base.exception.BusinessException;
import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.base.util.SystemUtil;
import com.xuchunchun.weixin.db.entity.WxCustomizemenu;
import com.xuchunchun.weixin.db.entity.WxCustomizemenuId;
import com.xuchunchun.weixin.platform.bean.CustomizeMenuBean;

public class CustomizeMenuUtil {
	private static Logger logger = Logger.getLogger(CustomizeMenuUtil.class);

	public static List<WxCustomizemenu> changeStrToEntity(String menuStr){
		return changeBeanToEntity(JsonUtil.parseToBean(menuStr, CustomizeMenuBean.class));
	}
	
	public static CustomizeMenuBean changeEntityToBean(List<WxCustomizemenu> menus){
		
		logger.info("start to change the database entity to customize menu bean....");
		
		CustomizeMenuBean customizeMenuBean = new CustomizeMenuBean();
		customizeMenuBean.setButton(new ArrayList<CustomizeMenuBean>());
		
		Collections.sort(menus, new Comparator<WxCustomizemenu>(){

			@Override
			public int compare(WxCustomizemenu src, WxCustomizemenu tar) {
				// TODO Auto-generated method stub
				return (int)(tar.getMenuLevel()-src.getMenuLevel());
			}
			
		});
		
		Map<String,List<CustomizeMenuBean>> relationMap = new HashMap<String,List<CustomizeMenuBean>>();
		
		for(int i=0;i<menus.size();i++){
			WxCustomizemenu menu = menus.get(i);
			
			logger.info("name:"+menu.getMenuName()+" level:"+menu.getMenuLevel());
			
			CustomizeMenuBean menuBean = new CustomizeMenuBean();
			menuBean.setKey(menu.getMenuKey());
			menuBean.setName(menu.getMenuName());
			menuBean.setType(menu.getMenuType());
						
			String menuSeq = menu.getId().getMenuSeq();
			String parentMenuSeq = menu.getParentMenuSeq();
			
			if(relationMap.containsKey(menuSeq)){
				menuBean.setSub_button(relationMap.get(menuSeq));
				relationMap.remove(menuSeq);
			}

						
			if(!StrUtil.isEmpty(parentMenuSeq)){
				if(!relationMap.containsKey(parentMenuSeq)){
					List<CustomizeMenuBean> CustomizeMenuBeanList = new ArrayList<CustomizeMenuBean>();
					relationMap.put(parentMenuSeq, CustomizeMenuBeanList);
				}
				relationMap.get(parentMenuSeq).add(menuBean);

			}else{
				customizeMenuBean.getButton().add(menuBean);
			}
			
		}
		
		return customizeMenuBean;
	}
	
	public static List<WxCustomizemenu> changeBeanToEntity(CustomizeMenuBean customizeMenuBean){
		
		logger.info("start to change the customize menu bean to database entity...");
		
		List<WxCustomizemenu> menus = new ArrayList<WxCustomizemenu>();
		
		List<CustomizeMenuBean> menuBeans = customizeMenuBean.getButton();
		
		_changeBeanToEntity(menus,menuBeans,null,1);
		
		return menus;
	}

	private static void _changeBeanToEntity(List<WxCustomizemenu> menus,List<CustomizeMenuBean> menuBeans,String parentMenuSeq,long level){
		
		for(int i=0;i<menuBeans.size();i++){
			
			CustomizeMenuBean menuBean = menuBeans.get(i);
			
			logger.info("process the bean with name "+menuBean.getName());
			
			String menuSeq = StrUtil.fill("L", '0', String.valueOf(level), 2)+StrUtil.fill("L", '0', String.valueOf(i+1), 8);
			long bankorgId = SystemUtil.getCurrBankorgId();
			WxCustomizemenuId wxCustomizemenuId = new WxCustomizemenuId();
			wxCustomizemenuId.setBankorgId(bankorgId);
			wxCustomizemenuId.setMenuSeq(menuSeq);
			
			WxCustomizemenu wxCustomizemenu = new WxCustomizemenu();
			wxCustomizemenu.setId(wxCustomizemenuId);
			wxCustomizemenu.setMenuKey(menuBean.getKey());
			wxCustomizemenu.setMenuLevel(level);
			wxCustomizemenu.setMenuName(menuBean.getName());
			wxCustomizemenu.setMenuType(menuBean.getType());
			wxCustomizemenu.setParentMenuSeq(parentMenuSeq);
			
			menus.add(wxCustomizemenu);
			
			List<CustomizeMenuBean> subMenuBeans = menuBean.getSub_button();
			
			if(subMenuBeans != null){
				_changeBeanToEntity(menus,subMenuBeans,menuSeq,level+1);
			}
		}
	}
	
	public static void checkMenuBean(CustomizeMenuBean menuBean) throws BusinessException{
		List<CustomizeMenuBean> menuBeans = menuBean.getButton();
		
		int size = menuBeans.size();
		
		if(size < 2 || size > 3){
			throw new BusinessLogicException("006", "");
		}
		
		_checkMenuBean(menuBeans , 1);
	}
	
	private static void _checkMenuBean(List<CustomizeMenuBean> menuBeans,int level) throws BusinessException{
		for(CustomizeMenuBean menuBean : menuBeans){
			logger.info("check the bean with name "+menuBean.getName());
			
			if("click".equals(menuBean.getType()) && menuBean.getKey() == null){
				throw new BusinessLogicException("001", menuBean.getName());
			}
			
			if(level == 1 && StrUtil.strlen(menuBean.getName()) > 16){
				throw new BusinessLogicException("002", menuBean.getName());
			}
			
			if(level > 1 && StrUtil.strlen(menuBean.getName()) > 40){
				throw new BusinessLogicException("003", menuBean.getName());
			}
			
			if(StrUtil.strlen(menuBean.getKey()) > 128){
				throw new BusinessLogicException("004", menuBean.getName());
			}
			
			List<CustomizeMenuBean> subMenuBeans = menuBean.getSub_button();
			
			int size = subMenuBeans.size();
			
			if(size < 2 || size > 5){
				throw new BusinessLogicException("005", menuBean.getName());
			}
			
			_checkMenuBean(subMenuBeans , level+1);

		}
	}
	
	public static void main(String args[]){
		String s = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"click\",\"name\":\"hello word\",\"key\":\"V1001_HELLO_WORLD\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
		
		
		
		CustomizeMenuBean a = JsonUtil.parseToBean(s, CustomizeMenuBean.class);
		
		List<WxCustomizemenu> entitys = changeBeanToEntity(a);
		
		for(WxCustomizemenu entity : entitys){
			System.out.println("----------------start-----------------");
			
			System.out.println("BankorgId:"+entity.getId().getBankorgId());
			System.out.println("MenuSeq:"+entity.getId().getMenuSeq());
			System.out.println("MenuKey:"+entity.getMenuKey());
			System.out.println("MenuName:"+entity.getMenuName());
			System.out.println("MenuType:"+entity.getMenuType());
			System.out.println("ParentMenuSeq:"+entity.getParentMenuSeq());
			System.out.println("MenuLevel:"+entity.getMenuLevel());

			
			System.out.println("----------------end-----------------");

		}
		
		CustomizeMenuBean b = changeEntityToBean(entitys);
		
		System.out.println(JsonUtil.parseBeanToJson(b));
	}
	
}
