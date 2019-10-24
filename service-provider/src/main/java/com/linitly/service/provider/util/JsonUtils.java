package com.linitly.service.provider.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author linxiunan
 * @date 2019/8/13 19:41
 * @description jackson工具类
 **/
public class JsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * @author linxiunan
     * @date 2019/8/13 19:52
     * @return java.lang.String
     * @description 对象转换为json，如果为null的属性转换为json时去除掉，则notNull属性传入true，反之则传入false
     */
    public static String objectToJson(Object data, boolean notNull) {
        if (notNull) {
            return objectToJsonNotNull(data);
        } else {
            return objectToJsonNull(data);
        }
    }

    private static String objectToJsonNull(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String objectToJsonNotNull(Object data) {
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * @author linxiunan
     * @date 2019/8/13 19:54
     * @return T
     * @description 对象转换为实体对象，如果需要忽略json中存在但实体对象中不存在的属性，则ignoreUnknown传入true，反之传入false
     */
    public static <T> T jsonToEntity(String jsonData, Class<T> beanType, boolean ignoreUnknown) throws JsonParseException, JsonMappingException, IOException {
        if (ignoreUnknown) {
            return jsonToEntityIgnoreUnknown(jsonData, beanType);
        } else {
            return jsonToEntityUnknownError(jsonData, beanType);
        }
    }

	private static <T> T jsonToEntityUnknownError(String jsonData, Class<T> beanType) throws JsonParseException, JsonMappingException, IOException {
		T t = MAPPER.readValue(jsonData, beanType);
		return t;
	}

	private static <T> T jsonToEntityIgnoreUnknown(String jsonData, Class<T> beanType) throws JsonParseException, JsonMappingException, IOException {
        // 忽略未知属性
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T t = MAPPER.readValue(jsonData, beanType);
		return t;
	}

	/**
	 * @author linxiunan
	 * @date 2019/8/13 19:55
	 * @return java.util.List<T>
	 * @description json转换为list数组
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) throws JsonParseException, JsonMappingException, IOException {
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		List<T> list = MAPPER.readValue(jsonData, javaType);
		return list;
	}

}
