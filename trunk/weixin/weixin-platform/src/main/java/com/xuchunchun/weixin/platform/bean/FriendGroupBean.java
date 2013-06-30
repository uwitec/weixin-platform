package com.xuchunchun.weixin.platform.bean;

public class FriendGroupBean {
	private String func;
	private String name;
	private int id = -1;
    private String token = "";
    
    public enum DefaultGroup{
    	unGroup(0,"未分组"),blackGroup(1,"黑名单"),startFlagGroup(2,"星标组");
    	
    	private int defaultGroupId;
    	private String defaultGroupName;
    	
    	private DefaultGroup(int defaultGroupId,String defaultGroupName){
    		this.defaultGroupId = defaultGroupId;
    		this.defaultGroupName = defaultGroupName;
    	}
    	
    	public int getDefaultGroupId(){
    		return this.defaultGroupId;
    	}
    	
    	public String getDefaultGroupName(){
    		return this.defaultGroupName;
    	}
    }

	
	public enum GroupFunc{
		add("add"),rename("rename"),delete("del");
		
		private String funcName;
		private GroupFunc(String funcName){
			this.funcName = funcName;
		}
		
		public String getFuncName(){
			return this.funcName;
		}
	}


	public String getFunc() {
		return func;
	}


	public void setFunc(GroupFunc func) {
		this.func = func.getFuncName();
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
