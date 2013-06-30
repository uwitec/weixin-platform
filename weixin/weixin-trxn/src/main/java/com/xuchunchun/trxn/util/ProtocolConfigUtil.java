package com.xuchunchun.trxn.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.protocol.cfg.Field;
import com.xuchunchun.trxn.protocol.cfg.ProtocolConfig;

public class ProtocolConfigUtil {
	private static Logger logger = Logger.getLogger(ProtocolConfigUtil.class);
	private static final String XML_ELE_TRXN_NAME = "trxnName";
	private static final String XML_ELE_PROTYPE = "proType";
	private static final String XML_ELE_REQUEST_FIELDS = "requestFields";
	private static final String XML_ELE_FIELD = "field";
	private static final String XML_ATT_NAME = "name";
	private static final String XML_ATT_SEQ = "seq";
	private static final String XML_ATT_LENGTH = "length";
	private static final String XML_ATT_LOCATION = "location";
	private static final String XML_ATT_TYPE = "type";
	private static final String XML_ATT_HEADLENGTH = "headLength";
	private static final String XML_ATT_HEAD_FILL_TYPE = "headFillType";
	private static final String XML_ATT_HEAD_FILL_CHAR = "headFillChar";
	private static final String XML_ATT_CONTENT_FILL_TYPE = "contentFillType";
	private static final String XML_ATT_CONTENT_FILL_CHAR = "contentFillChar";

	private static final String XML_ELE_RESPONSE_FIELDS = "responseFields";


	public static ProtocolConfig parseConfigData(File file) throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		ProtocolConfig config = new ProtocolConfig();
		
		String trxnName = root.element(XML_ELE_TRXN_NAME).getText();
		logger.debug("trxnName:"+trxnName);
		config.setTrxnName(trxnName);
		
		String proType = root.element(XML_ELE_PROTYPE).getText();
		config.setProType(proType);
		logger.debug("proType:"+proType);
				
		Element requestFieldsElement = root.element(XML_ELE_REQUEST_FIELDS);
		ArrayList<Field> requestFields = parseFields(requestFieldsElement);
		sortFields(requestFields);
		config.setRequestFields(requestFields);
		
		Element responseFieldsElement = root.element(XML_ELE_RESPONSE_FIELDS);
		ArrayList<Field> responseFields = parseFields(responseFieldsElement);
		sortFields(responseFields);
		config.setResponseFields(responseFields);
		
		return config;
	}
	
	public static void sortFields(ArrayList<Field> fields){
		Collections.sort(fields, new Comparator<Field>() {
			public int compare(Field src, Field tar) {
				return src.getSeq()-tar.getSeq();
			}
		});
	}
	
	private static ArrayList<Field> parseFields(Element element){
		ArrayList<Field> fields = new ArrayList<Field>();
		
		List<Element> fieldElements = element.elements(XML_ELE_FIELD);
		for(Element fieldElement : fieldElements){
			logger.info("start to parse a filed....");
			Field field = new Field();
			
			String fieldName = fieldElement.attributeValue(XML_ATT_NAME);
			int fieldSeq = Integer.parseInt(fieldElement.attributeValue(XML_ATT_SEQ));
			int fieldLength = -1;
			String fieldLengthStr = fieldElement.attributeValue(XML_ATT_LENGTH);
			if(!StrUtil.isEmpty(fieldLengthStr))
				fieldLength = Integer.parseInt(fieldLengthStr);
			String fieldLocation = fieldElement.attributeValue(XML_ATT_LOCATION);
			String type = fieldElement.attributeValue(XML_ATT_TYPE);
			int headLength = -1;
			String headLengthStr = fieldElement.attributeValue(XML_ATT_HEADLENGTH);
			if(!StrUtil.isEmpty(headLengthStr))
				headLength = Integer.parseInt(headLengthStr);
			String headFillType = fieldElement.attributeValue(XML_ATT_HEAD_FILL_TYPE);
			String contentFillType = fieldElement.attributeValue(XML_ATT_CONTENT_FILL_TYPE);
			
			String headFillCharStr = fieldElement.attributeValue(XML_ATT_HEAD_FILL_CHAR);
			String contentFillCharStr = fieldElement.attributeValue(XML_ATT_CONTENT_FILL_CHAR);
			
			char headFillChar = (StrUtil.isEmpty(headFillCharStr)?" ":headFillCharStr).toCharArray()[0];
			char contentFillChar = (StrUtil.isEmpty(contentFillCharStr)?" ":contentFillCharStr).toCharArray()[0];

			logger.debug("fieldName:"+fieldName);
			logger.debug("fieldSeq:"+fieldSeq);
			logger.debug("fieldLength:"+fieldLength);
			logger.debug("fieldLocation:"+fieldLocation);
			logger.debug("type:"+type);
			logger.debug("headLength:"+headLength);
			logger.debug("headFillType:"+headFillType);
			logger.debug("contentFillType:"+contentFillType);
			logger.debug("headFillChar:"+headFillChar);
			logger.debug("contentFillChar:"+contentFillChar);

			field.setName(fieldName);
			field.setSeq(fieldSeq);
			field.setLength(fieldLength);
			field.setLocation(fieldLocation);
			field.setType(type);
			field.setHeadLength(headLength);
			field.setHeadFillType(headFillType);
			field.setContentFillType(contentFillType);
			field.setHeadFillChar(headFillChar);
			field.setContentFillChar(contentFillChar);
			
			List<Element> childFieldElements = element.elements(XML_ELE_FIELD);
			
			if(childFieldElements != null && childFieldElements.size() > 0){
				
				field.setFields(parseFields(fieldElement));
			}
			fields.add(field);
		}
		
		return fields;
	}
}
