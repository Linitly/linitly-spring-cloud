/**
 * @author: linxiunan
 * @date: 2019/8/15 17:32
 * @descrption
 */
package com.linitly.service.provider.util;

import java.lang.reflect.Field;
import java.util.*;

public class BeanUtils {

    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author linxiunan
     * @date 2019/8/15 17:44
     * @description 对象转换为map
     */
    public static Map<String, Object> object2Map(Object object) {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] fields = getAllFields(object);
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            varName = varName.toLowerCase();
            try {
                boolean accessFlag = fields[i].isAccessible();//检查是否可以访问
                fields[i].setAccessible(true);
                Object o = fields[i].get(object);
                if (o != null) {
                    map.put(varName, o.toString());
                }
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * @return java.lang.String
     * @author linxiunan
     * @date 2019/8/15 17:36
     * @description 根据属性名获取方法名
     */
    public static String getMethodName(String filedName) {
        byte[] items = filedName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * @return java.lang.reflect.Field[]
     * @author linxiunan
     * @date 2019/8/15 17:37
     * @description 获取某个类的所有属性(包含父类)
     */
    public static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
