/**
 * 文件名：Queue.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：[描述]
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.base.system.queue;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

public interface Queue<T> {
	
	public boolean put(T T);
	
	public T pop();
	
	public int size();
	
	public boolean isNull();
}
