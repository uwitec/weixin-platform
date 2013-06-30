package com.xuchunchun.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.JavaIdentifierTransformer;
import net.sf.json.util.PropertyFilter;

public class JsonUtil {
	
	public static Map<String,Object> parserToMap(String s){
		Map<String,Object> map=new HashMap<String,Object>();
		JSONObject json=JSONObject.fromObject(s);
		Iterator keys=json.keys();
		while(keys.hasNext()){
			String key=(String) keys.next();
			String value=json.get(key).toString();
			if(value.startsWith("{")&&value.endsWith("}")){
				map.put(key, parserToMap(value));
			}else{
				map.put(key, value);
			}

		}
		return map;

	}
	
	public static String parseBeanToJson(Object o){
		JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter(){
            @Override
            public boolean apply(Object source, String name, Object value) {
                return (value == null) || "".equals(value);
            }
        });
        
        return JSONObject.fromObject(o, config).toString();

	}
	
	public static <T> T parseToBean(String jsonStr,Class<T> clazz){
		//jsonStr = jsonStr.replaceAll("[\\t\\n\\f\\r]", "");
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setArrayMode( JsonConfig.MODE_LIST );
		jsonConfig.setRootClass(clazz);
		
		Field[] fields = clazz.getDeclaredFields();
		
		Map<String,Class> classMap = new HashMap<String,Class>();
		
		for(Field field : fields){
			if(field.isEnumConstant())continue;
			String name = field.getName();
			Type fieldType = field.getGenericType();
			Class fieldClass = field.getType();
			if("java.util.List".equals(fieldClass.getName())){
				if(fieldType instanceof ParameterizedType){
					ParameterizedType parameterizedType = (ParameterizedType)fieldType;
					Type[] params = parameterizedType.getActualTypeArguments();
					if(params.length > 0){
						classMap.put(name, (Class)params[0]);

					}
					

				}
			}
			
		}
		
		if(classMap.size() > 0)jsonConfig.setClassMap(classMap);
		
		jsonConfig.setJavaIdentifierTransformer(new JavaIdentifierTransformer(){

			@Override
			public String transformToJavaIdentifier(String str) {
				char[] chars = str.toCharArray();
		        chars[0] = Character.toLowerCase(chars[0]);
		        return new String(chars);
			}
			
		});
		
		return (T) JSONObject.toBean( jsonObject, jsonConfig );

	}
	
	public static void main(String args[]){
		String s = "{\n\"Ret\": 302,\n\"ErrMsg\": \"/cgi-bin/indexpage?t=wxm-index&lang=zh_CN&token=1331604278\",\n\"ShowVerifyCode\": 0,\n\"ErrCode\": 0\n}";
		Map<String,Object> m = parserToMap(s);
		for(Map.Entry<String,Object> ss : m.entrySet()){
			System.out.println("["+ss.getKey()+"]"+":"+"["+ss.getValue()+"]");
		}
	}
	
}
