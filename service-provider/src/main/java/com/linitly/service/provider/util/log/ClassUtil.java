package com.linitly.service.provider.util.log;

import com.alibaba.fastjson.JSONObject;
import com.linitly.service.provider.annotation.LogIgnore;
import com.linitly.service.provider.helper.constant.GlobalConstant;
import com.linitly.service.provider.util.DateUtil;
import com.linitly.service.provider.util.JsonUtils;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassUtil {

	public static Long getEntityId(Object object, Field[] fields) throws Exception {
		Class<?> classes = object.getClass();
		Long entityId = null;
		for (Field field : fields) {
			if (field.getName().equals("id")) {
				Method method = classes.getMethod("get" + getMethodName(field.getName()));
				entityId = (Long) method.invoke(object);
				break;
			}
		}
		return entityId;
	}

	public static String getEntityLog(Object object, Field[] fields, String operation, String entityProperty)
			throws Exception {
		StringBuffer log = new StringBuffer();
		Class<?> classes = object.getClass();
		log.append(operation + entityProperty + ":: ");
		for (Field field : fields) {
			ApiModelProperty propertyAnno = field.getAnnotation(ApiModelProperty.class);
			LogIgnore logIgnoreAnno = field.getAnnotation(LogIgnore.class);
			if (propertyAnno != null && StringUtils.isNotBlank(propertyAnno.value()) && logIgnoreAnno == null) {
				if (field.getGenericType().toString().equals("class java.lang.String")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					String value = (String) method.invoke(object);
					if (StringUtils.isNotBlank(value)) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
				if (field.getGenericType().toString().equals("class java.lang.Integer")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					Integer value = (Integer) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
				if (field.getGenericType().toString().equals("class java.lang.Double")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					Double value = (Double) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
				if (field.getGenericType().toString().equals("double")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					Double value = (Double) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
				if (field.getGenericType().toString().equals("class java.lang.Boolean")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					Boolean value = (Boolean) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
				if (field.getGenericType().toString().equals("boolean")) {
					Method method = classes.getMethod("is" + getMethodName(field.getName()));
					Boolean value = (Boolean) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
				if (field.getGenericType().toString().equals("class java.util.Date")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					Date value = (Date) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(DateUtil.dateToString(value, DateUtil.dayOfDateFormat))
								.append("; ");
					}
				}
				if (field.getGenericType().toString().equals("class java.math.BigDecimal")) {
					Method method = classes.getMethod("get" + getMethodName(field.getName()));
					BigDecimal value = (BigDecimal) method.invoke(object);
					if (value != null) {
						log.append(propertyAnno.value()).append(": ").append(value).append("; ");
					}
				}
			}
		}
		return log.toString();
	}

	public static String getMethodName(String filedName) {
		byte[] items = filedName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static <T> String getLogTableName(T entity) {
		String className = entity.getClass().getName();
		StringBuffer logTableName = new StringBuffer();
		if (StringUtils.isNotBlank(className)) {
			if (className.contains(".")) {
				int last = className.lastIndexOf(".");
				className = className.substring(last + 1);
			}
			className = String.valueOf(className.charAt(0)).toLowerCase().concat(className.substring(1));
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(className);
			while (matcher.find()) {
				matcher.appendReplacement(logTableName, "_" + matcher.group(0).toLowerCase());
			}
			matcher.appendTail(logTableName);
			logTableName.append(GlobalConstant.LOG_TABLE_SUFFIX);
		}
		return StringUtils.isBlank(logTableName) ? null : logTableName.toString();
	}

	/**
	 * 获取某个类的所有属性(包含父类)
	 * @param object
	 * @return
	 */
	public static Field[] getAllFields(Object object) {
		Class clazz = object.getClass();
		List<Field> fieldList = new ArrayList<>();
		while (clazz != null){
			fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		Field[] fields = new Field[fieldList.size()];
		fieldList.toArray(fields);
		return fields;
	}

	public static String generatorLogJson(Object object) {
		Field[] fields = ClassUtil.getAllFields(object);
		String json = JsonUtils.objectToJson(object, false);
		JSONObject jsonObject = JSONObject.parseObject(json);
		for (Field field : fields) {
			LogIgnore logIgnoreAnno = field.getAnnotation(LogIgnore.class);
			if (logIgnoreAnno != null) {
				jsonObject.remove(field.getName());
			}
		}
		return jsonObject.toJSONString();
	}
}
