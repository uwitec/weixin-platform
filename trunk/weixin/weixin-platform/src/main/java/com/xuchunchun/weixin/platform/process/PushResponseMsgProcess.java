package com.xuchunchun.weixin.platform.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;

import com.xuchunchun.base.annotation.ResponseMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;

public class PushResponseMsgProcess {
	private static Logger logger = Logger.getLogger(PushResponseMsgProcess.class);
	private static DocumentFactory documentFactory = DocumentFactory.getInstance();
	private static String ROOT_STR = "xml";
	
	public static String parseObjToXml(PushMsg msg) throws Exception{
		ArrayList<Element> elements = autoGen(msg.getClass(),msg);
		Element root = documentFactory.createElement(ROOT_STR);
		for(Element element : elements){
			root.add(element);
		}
		return root.asXML();
	}
	
	private static ArrayList<Element> autoGen(Class<? extends Object> clazz,Object rspObj) throws Exception{
		ArrayList<Element> elements = new ArrayList<Element>();
		Class superClass = clazz.getSuperclass();
		
		if(superClass != null){
			ArrayList<Element> superElements = autoGen(superClass,rspObj);
			for(Element _element : superElements){
				elements.add(_element);
			}
		}
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			logger.info("field:"+field.getName());
			
			ResponseMsgField responseMsgField = field.getAnnotation(ResponseMsgField.class);
			
			if(responseMsgField == null)continue;
			
			String getValMethod = responseMsgField.getValMethod();
			String nodeName = responseMsgField.nodeName();
			boolean isChanged = responseMsgField.isChanged();
			boolean isComp = responseMsgField.isComp();
			boolean isArray = responseMsgField.isArray();
			String arrayNodeName = responseMsgField.arrayNodeName();
			String dateFormat = responseMsgField.dateFormat();
			
			Method mothod = clazz.getMethod(getValMethod, new Class[]{});
			Object fieldObj = mothod.invoke(rspObj, new Object[]{});
			Element node = documentFactory.createElement(nodeName);
			
			if(!isComp){
				String content = formatNodeContent(fieldObj,dateFormat);
				if(content == null)throw new Exception("the value is null");
				if(isChanged){
					node.addCDATA(content);
				}else{
					node.addText(content);
				}
				
			}else{
				if(isArray){
					List fieldObjList = (List)fieldObj;
					for(Object _fieldObj : fieldObjList){
						Element arrayNode = documentFactory.createElement(arrayNodeName);
						ArrayList<Element> subElements = autoGen(_fieldObj.getClass(),_fieldObj);
						for(Element _element : subElements){
							arrayNode.add(_element);
						}
						node.add(arrayNode);
					}
				}else{
					ArrayList<Element> subElements = autoGen(fieldObj.getClass(),fieldObj);
					for(Element _element : subElements){
						node.add(_element);
					}
				}
			}
			elements.add(node);
		}
		
		return elements;
	}
	
	private static String formatNodeContent(Object o,String dateFormat){
		if(o == null){
			return null;
		}
		String type = o.getClass().getName();
		if("java.util.Date".equalsIgnoreCase(type)){
			if("".equals(dateFormat)){
				return String.valueOf(((java.util.Date)o).getTime());
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				return sdf.format((java.util.Date)o);
			}
		}else if("java.sql.Date".equalsIgnoreCase(type)){
			if("".equals(dateFormat)){
				return String.valueOf(((java.sql.Date)o).getTime());
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				return sdf.format((java.sql.Date)o);
			}
		}
		else {
			return String.valueOf(o);
		}
	}
}
