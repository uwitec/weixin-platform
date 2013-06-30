package com.xuchunchun.trxn.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.xuchunchun.trxn.msg.cfg.Field;
import com.xuchunchun.trxn.msg.cfg.KeyField;
import com.xuchunchun.trxn.msg.cfg.RequestFields;
import com.xuchunchun.trxn.msg.cfg.ResponseFields;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;

public class TrxnConfigUtil {
	private static Logger logger = Logger.getLogger(TrxnConfigUtil.class);
	private static final String XML_ELE_TRXN_NAME = "trxnName";
	private static final String XML_ELE_TRXN_DESC = "trxnDesc";
	private static final String XML_ELE_REQUEST_FIELDS = "requestFields";
	private static final String XML_ELE_FIELD = "field";
	private static final String XML_ATT_NAME = "name";
	private static final String XML_ATT_DESC = "desc";
	private static final String XML_ATT_MULTY = "multy";
	private static final String XML_ATT_TYPE = "type";
	private static final String XML_ATT_CHECKEXPR = "checkExpr";
	private static final String XML_ATT_ISNULL = "isNull";
	private static final String XML_ATT_DATE_FORMAT = "dateFormat";
	private static final String XML_ATT_COUNTFIELD = "countField";
	private static final String XML_ATT_FILLVALUE = "fillValue";
	private static final String XML_ATT_MEMOFIELD = "memoFiled";
	private static final String XML_ELE_RESPONSE_FIELDS = "responseFields";
	private static final String XML_ELE_KEY_FIELDS = "keyFields";
	private static final String XML_ELE_KEY_FIELD = "keyField";
	private static final String XML_ELE_REASON_FIELD = "reasonField";
	private static final String XML_ATT_KEY_NAME = "name";
	private static final String XML_ELE_PRELOADS = "preloads";
	private static final String XML_ELE_PRELOAD = "preload";
	private static final String XML_ATT_ISMULTY = "multy";




	public static TrxnConfig parseConfigData(File file) throws Exception{
		SAXReader reader = new SAXReader();
		Document doc = reader.read(file);
		Element root = doc.getRootElement();
		TrxnConfig config = new TrxnConfig();
		
		String trxnName = root.element(XML_ELE_TRXN_NAME).getText();
		logger.debug("trxnName:"+trxnName);
		config.setTrxnName(trxnName);
		
		String trxnDesc = root.element(XML_ELE_TRXN_DESC).getText();
		config.setTrxnDesc(trxnDesc);
		logger.debug("trxnDesc:"+trxnDesc);
		
		RequestFields requestFields = new RequestFields();
		Element requestFieldsElement = root.element(XML_ELE_REQUEST_FIELDS);
		requestFields.setFields(parseFields(requestFieldsElement));
		
		logger.info("load the preload for the transaction....");
				
		List<Element> preloadElements = requestFieldsElement.element(XML_ELE_PRELOADS).elements(XML_ELE_PRELOAD);
		
		for(Element preloadElement : preloadElements){
			requestFields.addPreloads(preloadElement.getText(),Boolean.parseBoolean(preloadElement.attributeValue(XML_ATT_ISMULTY)));
		}
		
		config.setRequestFields(requestFields);
		
		ResponseFields responseFields = new ResponseFields();
		Element responseFieldsElement = root.element(XML_ELE_RESPONSE_FIELDS);
		responseFields.setFields(parseFields(responseFieldsElement));
		
		Element keyElements = responseFieldsElement.element(XML_ELE_KEY_FIELDS);
		List<Element> keyFieldElements = keyElements.elements(XML_ELE_KEY_FIELD);
		for(Element keyFieldElement : keyFieldElements){
			KeyField KeyField = new KeyField();
			String keyFieldName = keyFieldElement.attributeValue(XML_ATT_KEY_NAME);
			String keyFieldText = keyFieldElement.getText();
			KeyField.setName(keyFieldName);
			KeyField.setValText(keyFieldText);
			logger.debug("keyFieldName:"+keyFieldName);
			logger.debug("keyFieldText:"+keyFieldText);

			responseFields.addKeyField(KeyField);
			
		}
		

		
		String reasonField = responseFieldsElement.elementText(XML_ELE_REASON_FIELD);

		logger.info("reasonField:"+reasonField);
		
		responseFields.setReasonField(reasonField);
		
		config.setResponseFields(responseFields);
		
		return config;
	}
	
	private static HashMap<String,Field> parseFields(Element element){
		HashMap<String,Field> fields = new HashMap<String,Field>();
		
		List<Element> fieldElements = element.elements(XML_ELE_FIELD);
		for(Element fieldElement : fieldElements){
			Field field = new Field();
			logger.info("start to parse a filed:"+field.getName());

			String fieldName = fieldElement.attributeValue(XML_ATT_NAME);
			String fieldDesc = fieldElement.attributeValue(XML_ATT_DESC);
			boolean fieldMulty = Boolean.parseBoolean(fieldElement.attributeValue(XML_ATT_MULTY));
			String fieldType = fieldElement.attributeValue(XML_ATT_TYPE);
			String dateFormat = fieldElement.attributeValue(XML_ATT_DATE_FORMAT);
			String checkExpr = fieldElement.attributeValue(XML_ATT_CHECKEXPR);
			boolean isNull = Boolean.parseBoolean(fieldElement.attributeValue(XML_ATT_ISNULL));
			String countField = fieldElement.attributeValue(XML_ATT_COUNTFIELD);
			String fillValue = fieldElement.attributeValue(XML_ATT_FILLVALUE);
			String memoFiled = fieldElement.attributeValue(XML_ATT_MEMOFIELD);

			logger.debug("fieldName:"+fieldName);
			logger.debug("fieldDesc:"+fieldDesc);
			logger.debug("fieldMulty:"+fieldMulty);
			logger.debug("fieldType:"+fieldType);
			logger.debug("dateFormat:"+dateFormat);
			logger.debug("checkExpr:"+checkExpr);
			logger.debug("isNull:"+isNull);
			logger.debug("countField:"+countField);
			logger.debug("fillValue:"+fillValue);
			logger.debug("memoFiled:"+memoFiled);


			field.setName(fieldName);
			field.setDesc(fieldDesc);
			field.setMulty(fieldMulty);
			field.setType(fieldType);
			field.setDateFormat(dateFormat);
			field.setCheckExpr(checkExpr);
			field.setNull(isNull);
			field.setCountField(countField);
			field.setFillValue(fillValue);
			field.setMemoFiled(memoFiled);
			
			if(field.isMulty()){
				field.setFields(parseFields(fieldElement));
			}
			fields.put(field.getName(), field);
		}
		
		return fields;
	}
}
