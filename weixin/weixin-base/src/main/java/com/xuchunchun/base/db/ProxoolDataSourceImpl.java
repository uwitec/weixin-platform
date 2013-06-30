/**
 * 文件名：ProxoolDataSourceImpl.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：[描述]
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-8-26
 */
package com.xuchunchun.base.db;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolDataSource;

import com.xuchunchun.base.util.PWD;

/**
 * 连接池代理实现类
 * @author    tpf
 * @version   1.0  2012-8-26
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ProxoolDataSourceImpl extends ProxoolDataSource {

	protected final static Logger log = Logger.getLogger(ProxoolDataSourceImpl.class);

	public String getPassword() {
		String pwd = super.getPassword();
		return PWD.dec(pwd);
	}
	public static void main(String[] args) throws IOException {
		//String r = PWD.enc("hanhenyinpishuang");
		//System.out.println(r);
		String dec = PWD.dec("oROzOjuhhMb5CfS9bIwwLCy0CcjI");
		System.out.println(dec);
	}
}
