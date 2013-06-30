package com.xuchunchun.base.db;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;

import com.xuchunchun.base.util.PWD;

public class PropertiesEncryptFactoryBean implements FactoryBean  {
	
	protected final static Logger log = Logger.getLogger(PropertiesEncryptFactoryBean.class);

	private Properties properties;
	
	@Override
	public Object getObject() throws Exception {
		return getProperties();   
	}

	@Override
	public Class getObjectType() {
		return java.util.Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
	
	public Properties getProperties() {   
		return properties;   
	}  
	
	public void setProperties(Properties inProperties) {   
		this.properties = inProperties;   
		String originalPassword = properties.getProperty("password");   
		
		if (originalPassword != null){   
			String newPassword = PWD.dec(originalPassword);   
			log.debug("--------------------------------password:["+newPassword+"]");
			properties.put("password", newPassword);   
		}   
	}  
	
	public static void main(String args[]){
		String pwd = "weixinTest";
		String pwd_enc = PWD.enc(pwd);
		System.out.println(pwd_enc);
		String pwd_dec = PWD.dec(pwd_enc);
		System.out.println(pwd_dec);
	}
	
}
