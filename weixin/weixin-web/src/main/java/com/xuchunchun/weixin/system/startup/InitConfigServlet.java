package com.xuchunchun.weixin.system.startup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;

import com.xuchunchun.trxn.client.ClientSocketFactory;
import com.xuchunchun.trxn.msg.NamedSqlFactory;
import com.xuchunchun.trxn.msg.TrxnMsgConfigFactory;
import com.xuchunchun.trxn.msg.TrxnMsgFactory;
import com.xuchunchun.weixin.seq.SystemSeqFactory;

import cn.sunline.suncard.tag.dict.BizDictManager;

/**
 * 初始化配置信息
 * @author    zhoub
 * @version   1.0  2012-2-15
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

public class InitConfigServlet extends HttpServlet {
	
	Logger logger = Logger.getLogger(InitConfigServlet.class);

	/**
	 * Constructor of the object.
	 */
	public InitConfigServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		try {
			//初始化业务字典
			String dictConfigLocation = this.getServletContext().getInitParameter("dictConfigLocation");
			dictConfigLocation = this.getServletContext().getRealPath("/") + dictConfigLocation;
			BizDictManager.initDocument(dictConfigLocation);
			
			//初始化批量文件路径
//			String batchfilePath = this.getServletContext().getInitParameter("batchFileConfigLocation");
//			ConfigFileLoadFactory.getInstance().loadConfiguration(this.getServletContext().getRealPath("/"),batchfilePath);
			
			//初始化核准件生成路径
//			String filePath = this.getServletContext().getRealPath("/")+SystemUtil.getAppFileDir();
//			AcdExportFilesServiceImpl.setFilePath(filePath);
			
			
			
		}  catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e.getCause());
		} 
		
		
		
	}

}
