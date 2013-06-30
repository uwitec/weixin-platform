package com.xuchunchun.weixin.platform.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.xuchunchun.base.util.JsonUtil;

public class CustomizeMenuBean {
	
	public static enum TYPE{
		click
	};
	
	private List<CustomizeMenuBean> button = null;
	
	private List<CustomizeMenuBean> sub_button = null;
	
	private String type;
	
	private String name;

	private String key;

	public List<CustomizeMenuBean> getButton() {
		return button;
	}

	public void setButton(List<CustomizeMenuBean> button) {
		this.button = button;
	}

	public List<CustomizeMenuBean> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<CustomizeMenuBean> sub_button) {
		this.sub_button = sub_button;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static void main(String args[]){
		
		String s = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"click\",\"name\":\"hello word\",\"key\":\"V1001_HELLO_WORLD\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
		
		
		
		CustomizeMenuBean a = JsonUtil.parseToBean(s, CustomizeMenuBean.class);
		
		
		
		System.out.println(JsonUtil.parseBeanToJson(a));
		//JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.setArrayMode( JsonConfig.MODE_OBJECT_ARRAY );
		//jsonConfig.setRootClass(clazz);
		
		//jsonObject.getJSONArray("button");
		
	}
	
}
