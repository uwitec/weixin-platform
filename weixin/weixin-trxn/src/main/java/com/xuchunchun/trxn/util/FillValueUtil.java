package com.xuchunchun.trxn.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.base.dao.DefaultDao;
import com.xuchunchun.base.util.ClassUtil;
import com.xuchunchun.base.util.DatabaseUtil;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.trxn.msg.NamedSqlFactory;
import com.xuchunchun.trxn.msg.TrxnMessage;
import com.xuchunchun.trxn.msg.cfg.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillValueUtil {
	private static Logger logger = Logger.getLogger(FillValueUtil.class);
	private static final String COMMON_PATTERN_STR = "((\\[[K|S|C|B|T]\\][\\w]+)(<([\\d]+)>)?(\\.([\\w]+))?)\\[/[K|S|C|B|T]\\]";
	private static final String SQL_PATTERN_STR = "(\\[[K|S|C|B|T]\\][\\w]+)(<([\\d]+)>)?(\\.([\\w]+))?";
	private static final String LOOP_PATTERN_STR = "\\[LOOP\\s<(.*)>\\](.*)\\[/LOOP\\]";

	public static enum KEY_TYPE {
		K, S, C, B, T
	};

	public static enum BASE_FIELD {
		CURR_DATE
	};

	public static String formatStr(String srcStr,
			HashMap<String, Object> trxnCache, TrxnMessage msg, Object parentObj)
			throws Exception {
		logger.info("process the loop object...");
		Pattern loopPattern = Pattern.compile(LOOP_PATTERN_STR);
		Matcher loopMatcher = loopPattern.matcher(srcStr);
		while (loopMatcher.find()) {
			String fieldDesc = loopMatcher.group(1);
			String loopStr = loopMatcher.group(2);
			logger.info("find a loop object with name:" + fieldDesc);
			List loopObjs = (List) getFillValue(trxnCache, fieldDesc, null,
					true, msg);
			StringBuilder replaceStr = new StringBuilder();
			for (Object loopObj : loopObjs) {
				replaceStr.append(formatStr(loopStr, trxnCache, msg, loopObj));
				replaceStr.append(System.getProperty("line.separator"));
			}
			srcStr = srcStr.replaceFirst(LOOP_PATTERN_STR,
					replaceStr.toString());
		}

		logger.info("process the common object string...");

		Pattern commonPattern = Pattern.compile(COMMON_PATTERN_STR);
		Matcher commonMatcher = commonPattern.matcher(srcStr);

		while (commonMatcher.find()) {
			String commonFieldDesc = commonMatcher.group(1);
			logger.info("find a common string :" + commonFieldDesc);
			srcStr = srcStr.replaceFirst(COMMON_PATTERN_STR, TrxnMessageUtil
					.changeObjToStr(
							getFillValue(trxnCache, commonFieldDesc, parentObj,
									true, msg),
							BaseConstants.DEFAULT_DATE_FOEMAT));
		}

		return srcStr;
	}

	public static boolean fillReqMessage(HashMap<String, Object> trxnCache,
			TrxnMessage msg, HashMap<String, Field> fields) throws Exception {
		return fillReqMessage(trxnCache, msg, fields, null);
	}

	public static boolean fillReqMessage(HashMap<String, Object> trxnCache,
			TrxnMessage msg, HashMap<String, Field> fields, Object parent)
			throws Exception {
		boolean result = true;

		for (Map.Entry<String, Field> _field : fields.entrySet()) {
			String name = _field.getKey();
			Field field = _field.getValue();

			String fieldDesc = field.getFillValue();
			
			if(StrUtil.isEmpty(fieldDesc))continue;
			
			String countField = field.getCountField();

			boolean isMulty = field.isMulty();

			Object obj = null;

			String expr = "^\\[[K|S|C|B|T]\\]\\w*$";
			Pattern pattern = Pattern.compile(expr);
			Matcher matcher = pattern.matcher(fieldDesc);

			if (matcher.matches()) {
				obj = getFillValue(trxnCache, fieldDesc, parent, isMulty, null);
			} else {
				obj = fieldDesc;
			}

			if (!isMulty) {
				result = msg.setSingleObj(name, obj);
				
			} else {
				ArrayList<TrxnMessage> messages = msg.getMultyObj(name);
				List parentObjs = (List) obj;
				
				if(!StrUtil.isEmpty(countField)){
					logger.info("set count of the list for field:"+countField);
					msg.setSingleObj(countField, messages.size());
				}
				
				for (int i = 0; i < messages.size(); i++) {
					TrxnMessage message = messages.get(i);
					Object _parentObj = parentObjs.get(i);
					result = fillReqMessage(trxnCache, message,
							field.getFields(), _parentObj);
					if (!result)
						break;
				}
			}

			if (!result)
				break;

		}

		return result;

	}

	public static void doPreload(HashMap<String, Object> trxnCache,
			HashMap<String, Boolean> fieldDescs) throws Exception {
		for (Map.Entry<String, Boolean> _fieldDesc : fieldDescs.entrySet()) {
			String fieldDesc = _fieldDesc.getKey();
			Boolean isMulty = _fieldDesc.getValue();
			logger.info("preload the value with name:" + fieldDesc);
			getFillValue(trxnCache, fieldDesc, null, isMulty, null);
		}
	}

	public static Object getFillValue(HashMap<String, Object> trxnCache,
			String fieldDesc, Object parentObj, boolean isMulty, TrxnMessage msg)
			throws Exception {

		String[] fieldArray = fieldDesc.split(".");

		boolean isArray = fieldArray.length == 0 ? false : true;

		String fieldKey = null;
		String fieldName = null;

		if (isArray) {
			fieldKey = fieldArray[0];
			fieldName = fieldArray[1];
		} else
			fieldKey = fieldDesc;

		int index = -1;

		String expr = "(\\w*)<([0-9]+)>$";
		Pattern pattern = Pattern.compile(expr);
		Matcher matcher = pattern.matcher(fieldKey);
		if (matcher.matches()) {
			index = Integer.parseInt(matcher.group(2));
			fieldKey = matcher.group(1);
		}

		Object _result = null;

		if (trxnCache.containsKey(fieldKey))
			_result = trxnCache.get(fieldKey);
		else {

			String keyInd = fieldKey.substring(1, 2);

			switch (Enum.valueOf(KEY_TYPE.class, keyInd)) {
			case K: {
				throw new Exception("this is a key value ,can not be null.....");
			}
			case B: {
				_result = getBaseValue(trxnCache, fieldKey);
				break;
			}
			case S: {
				_result = getNamedSqlValue(trxnCache, fieldKey, isMulty);
				break;
			}
			case C: {
				if(parentObj instanceof TrxnMessage){
					TrxnMessage _parentObj = (TrxnMessage)parentObj;
					_result = ClassUtil.getObj(_parentObj.getMessage(), fieldKey.substring(3));

				}else{
					_result = ClassUtil.getObj(parentObj, fieldKey.substring(3));

				}
				break;
			}
			case T: {
				_result = getTrxnValue(trxnCache, msg, fieldKey.substring(3));
				break;
			}
			default:
				throw new Exception("there is not a type with name " + keyInd
						+ " ,config error....");
			}

		}

		if (_result == null) {
			_result = "";
		}

		if (index != -1) {
			try {
				_result = ((List) _result).get(index);
			} catch (Exception e) {
				throw new Exception(
						"config error , the result is not a array...resean:"
								+ e.getMessage());
			}
		}

		if (fieldName != null) {
			_result = ClassUtil.getObj(_result,
					StrUtil.parseFiledName(fieldName));
		}

		return _result;
	}

	public static Object getTrxnValue(HashMap<String, Object> trxnCache,
			TrxnMessage msg, String fieldKey) throws Exception {
		if (msg == null)
			throw new Exception(
					"if key value is T,the message can not be null....");
		Object obj = msg.getMessage().get(fieldKey);
		trxnCache.put(fieldKey, obj);
		return obj;
	}

	public static Object getNamedSqlValue(HashMap<String, Object> trxnCache,
			String namedSqlKey, boolean isMulty) throws Exception {
		logger.info("get the value of the named sql:" + namedSqlKey);

		String namedSql = NamedSqlFactory.getInstance().getNamedSql(
				namedSqlKey.substring(3));

		if (namedSql == null || "".equalsIgnoreCase(namedSql)) {
			logger.info("there is not sql configed in the file");
			throw new Exception("there is not sql configed in the file");
		} else {

			Pattern pattern = Pattern.compile(SQL_PATTERN_STR);
			Matcher matcher = pattern.matcher(namedSql);

			while (matcher.find()) {
				String _namedSqlKey = matcher.group();
				logger.info("match a string :" + _namedSqlKey);

				String fullKey = matcher.group(1);

				String index = matcher.group(3);

				String fieldName = matcher.group(5);

				String replaceStr = "";

				Object obj = getFillValue(trxnCache, fullKey, null,
						index == null ? false : true, null);

				trxnCache.put(fullKey, obj);

				if (obj != null) {
					if (index != null) {
						try {
							obj = ((List) obj).get(Integer.parseInt(index));
						} catch (Exception e) {
							throw new Exception(
									"config error , the result is not a array...resean:"
											+ e.getMessage());
						}
					}

					if (fieldName != null) {
						obj = ClassUtil.getObj(obj, fieldName);
					}
				}

				replaceStr = TrxnMessageUtil.changeObjToStr(obj,
						BaseConstants.DEFAULT_DATE_FOEMAT);

				namedSql = namedSql.replaceFirst(SQL_PATTERN_STR, replaceStr);

			}

			logger.info("finish generating the sql:" + namedSql);

			return DatabaseUtil.getInstance().excuteSql(namedSql, isMulty);

		}
	}

	public static Object getBaseValue(HashMap<String, Object> trxnCache,
			String fieldKey) throws Exception {
		logger.info("get base value:" + fieldKey);
		String key = fieldKey.substring(3);

		Object obj = null;

		switch (Enum.valueOf(BASE_FIELD.class, key)) {
		case CURR_DATE: {
			obj = new Date();
			break;
		}

		}
		if (obj == null) {
			throw new Exception("the field name :" + fieldKey
					+ " is not configed");
		} else {
			trxnCache.put(fieldKey, obj);
			return obj;
		}
	}

	public static void main(String args[]) {
		String configVal = "sahdklaHDAKSD dadsad=[K]dsgdh_jagd<1>\"";
		Pattern pattern = Pattern.compile(SQL_PATTERN_STR);
		Matcher matcher = pattern.matcher(configVal);
		while (matcher.find()) {
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
			System.out.println(matcher.group(4));
			System.out.println(matcher.group(5));
			System.out.println(matcher.group(6));

		}
	}

}
