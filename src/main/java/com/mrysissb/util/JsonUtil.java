package com.mrysissb.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

public class JsonUtil {
//	/**
//	 * JAVA对象转换成JSON字符串
//	 * @param obj
//	 * @return
//	 */	
//	public static String objectToJsonStr(Object obj){
//		JSONObject jsonStu = JSONObject.fromObject(obj); 
//        String jsonStr = jsonStu.toString();
//        return jsonStr;
//	}
//	/**
//	 * JSON字符串转换成JAVA对象
//	 * {\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}
//	 * @param jsonStr
//	 * @return
//	 */	
//	public static Object JsonStrTobject(String jsonStr){
//
//		JSONObject obj =JSONObject.fromObject(jsonStr);
//		Object jb =JSONObject.toBean(obj);
//		return jb;
//	}
//	/**
//	 * ArrayStr转换成JAVA对象
//	 * [{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]
//	 * @param jsonStr
//	 * @return
//	 */	
//	
//	public static Object ArrayStrTobject(String arrayStr,Class c){
//
//		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
//		List<Object> data=new ArrayList<Object>();
//		for (int i = 0; i < jsonArray.size(); i++) {
//			JSONObject jsonObject2=JSONObject.fromObject(jsonArray.get(i));
//			Object stu=JSONObject.toBean(jsonObject2,c);
//			data.add(stu);
//		}
//		return data;
//	}
//	
//	
//	/**
//     * List集合转换为Json
//     * @param list
//     * @return
//     */
//    public static String list2json(List<Object> list) {
//        StringBuilder json = new StringBuilder();
//        json.append("[");
//        if (list != null && list.size() > 0) {
//            for (Object obj : list) {
//                json.append(objectToJsonStr(obj));
//                json.append(",");
//            }
//            json.setCharAt(json.length() - 1, ']');
//        } else {
//            json.append("]");
//        }
//        return json.toString();
//    }
//    
//    /**
//     * json 转 List<T>
//     */
//    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
//    	List<T> list = new ArrayList<T>(); 
//    	JSONArray jsonArray = JSONArray.fromObject(jsonString);//把String转换为json 
//    	//list = JSONArray.toList(jsonArray,clazz);//这里的t是Class<T> 
//    	list = (List<T>) JSONArray.toCollection(jsonArray,clazz);//这里的t是Class<T> 
//        return list;
//    }

	/**
	 * 对象转jsonstr
	 * @param obj
	 * @return 
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String objectToJsonStr(Object obj) {
		String jsonStr=null;
		try {
			jsonStr= new ObjectMapper().writeValueAsString(obj);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}
}
