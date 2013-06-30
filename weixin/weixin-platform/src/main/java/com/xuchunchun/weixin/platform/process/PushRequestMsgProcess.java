package com.xuchunchun.weixin.platform.process;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EnumType;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xuchunchun.base.annotation.RequestMsgField;
import com.xuchunchun.weixin.platform.msg.PushMsg;
import com.xuchunchun.weixin.platform.msg.req.EventMsgRequest;
import com.xuchunchun.weixin.platform.msg.req.LocationMsgRequest;
import com.xuchunchun.weixin.platform.msg.req.PicMsgRequest;
import com.xuchunchun.weixin.platform.msg.req.TextMsgRequest;
import com.xuchunchun.weixin.platform.msg.req.UrlMsgRequest;

public class PushRequestMsgProcess {
	private static Logger logger = Logger.getLogger(PushRequestMsgProcess.class);
	
	private static Element parseDocFromXml(String xmlStr) throws DocumentException{
		Document doc = DocumentHelper.parseText(xmlStr);
		return doc.getRootElement();
	}
	
	public static PushMsg parseXmlToObj(String xmlStr) throws Exception{
		Element root = parseDocFromXml(xmlStr);
	
		PushMsg head = aotowire(PushMsg.class,root);
		
		PushMsg msgObj = aotowire(getType(head.getMsgType()),root);
		msgObj.setCreateTime(head.getCreateTime());
		msgObj.setFromUserName(head.getFromUserName());
		msgObj.setMsgType(head.getMsgType());
		msgObj.setToUserName(head.getToUserName());
		
		return msgObj;
	}
	
	private static <T> T aotowire(Class<T> clazz,Element element) throws Exception{
		if(clazz == null)throw new Exception("the message type is error...");
		T t = clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			logger.info("field:"+field.getName());
			RequestMsgField requestMsgField = field.getAnnotation(RequestMsgField.class);
			
			if(requestMsgField == null)continue;
			
			String addValMethod = requestMsgField.addValMethod();
			String nodeName = requestMsgField.nodeName();
			boolean isChanged = requestMsgField.isChanged();
			boolean isComp = requestMsgField.isComp();
			Class parameter = requestMsgField.parameter();
			boolean isArray = requestMsgField.isArray();
			String arrayNodeName = requestMsgField.arrayNodeName();
			
			
			Element _element = element.element(nodeName);
			Method mothod = clazz.getMethod(addValMethod, new Class[]{parameter});
			
			if(!isComp){
				String _content = _element.getText();
				Object content = changeType(parameter,_content);
				if(content == null)throw new Exception("the parameter changed error with value:"+_content);
				mothod.invoke(t, new Object[]{content});
			}else{
				Class filedClass = field.getType();
				
				if(isArray){
					List<Element> subElements = _element.elements(arrayNodeName);
					
					for(Element subElement : subElements){
						mothod.invoke(t, new Object[]{aotowire(filedClass,subElement)});
					}
					
				}else{
					mothod.invoke(t, new Object[]{aotowire(filedClass,_element)});
				}
			}
			
			
		}
		
		return t;
		
	}
	
	private static Object changeType(Class clazz,String data){
		String type = clazz.getName();
		if("java.lang.String".equalsIgnoreCase(type))return data;
		else if("java.lang.Long".equalsIgnoreCase(type)||"long".equalsIgnoreCase(type)){
			return Long.parseLong(data);
		}else if("java.lang.Integer".equalsIgnoreCase(type)||"int".equalsIgnoreCase(type)){
			return Integer.parseInt(data);
		}
		else{
			return null;
		}
		
	}
	
	private static Class getType(String msgType){
		switch(EnumType.valueOf(PushMsg.requestMsgType.class, msgType)){
			case text:
				return TextMsgRequest.class;
			case image:
				return PicMsgRequest.class;
			case location:
				return LocationMsgRequest.class;
			case link:
				return UrlMsgRequest.class;
			case event:
				return EventMsgRequest.class;
			default:return null;
				
		}
	}
	
}
