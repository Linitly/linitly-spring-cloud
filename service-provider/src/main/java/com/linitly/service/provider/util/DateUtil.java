package com.linitly.service.provider.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author linxiunan
 * @date 2019/8/22 15:02
 * @description 时间处理工具类
 */
public class DateUtil {

    public static SimpleDateFormat noBarDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static SimpleDateFormat dayOfDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat secondOfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @author linxiunan
     * @date 2019/8/22 14:57
     * @return java.util.Date
     * @description 将当前时间加上一天，返回
     */
    public static Date addOneDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 14:57
     * @return java.util.Date
     * @description 将传入时间加上一天，返回
     */
    public static Date addOneDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 14:57
     * @return java.util.Date
     * @description 将当前时间加上一天，根据传入的格式返回格式化后的日期
     */
    public static String addOneDay(SimpleDateFormat format) {
        Date date = addOneDay();
        return format.format(date);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 14:59
     * @return java.lang.String
     * @description 将传入时间加上一天，根据传入的格式返回格式化后的日期
     */
    public static String addOneDay(Date date, SimpleDateFormat format) {
        Date resultDate = addOneDay(date);
        return format.format(resultDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:00
     * @return java.lang.String
     * @description 将当前时间加上一个月，返回
     */
    public static Date addOneMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:00
     * @return java.lang.String
     * @description 将传入时间加上一个月，返回
     */
    public static Date addOneMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:00
     * @return java.lang.String
     * @description 将当前时间加上一个月，根据传入的格式返回格式化后的日期
     */
    public static String addOneMonth(SimpleDateFormat format) {
        Date resultDate = addOneMonth();
        return format.format(resultDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:00
     * @return java.lang.String
     * @description 将传入时间加上一个月，根据传入的格式返回格式化后的日期
     */
    public static String addOneMonth(Date date, SimpleDateFormat format) {
        Date resultDate = addOneMonth(date);
        return format.format(resultDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:23
     * @return java.lang.String
     * @description 将当前时间减一个月，返回
     */
    public static Date cutOneMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:23
     * @return java.lang.String
     * @description 将传入时间减一个月，返回
     */
    public static Date cutOneMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:23
     * @return java.lang.String
     * @description 将当前时间减一个月，根据传入的格式返回格式化后的日期
     */
    public static String cutOneMonth(SimpleDateFormat format) {
        Date resultDate = cutOneMonth();
        return format.format(resultDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:23
     * @return java.lang.String
     * @description 将传入时间减一个月，根据传入的格式返回格式化后的日期
     */
    public static String cutOneMonth(Date date, SimpleDateFormat format) {
        Date resultDate = cutOneMonth(date);
        return format.format(resultDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:53
     * @return java.util.Date
     * @description 返回当前时间添加几天之后的时间
     */
    public static Date addFewDays(int dayNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayNumber);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:53
     * @return java.util.Date
     * @description 返回传入时间添加几天之后的时间
     */
    public static Date addFewDays(Date date, int dayNumber) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayNumber);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:53
     * @return java.util.Date
     * @description 返回当天时间添加几天之后的时间，根据传入的格式返回格式化后的日期
     */
    public static String addFewDays(int dayNumber, SimpleDateFormat format) {
        Date date = addFewDays(dayNumber);
        return format.format(date);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:53
     * @return java.util.Date
     * @description 返回当天时间添加几天之后的时间，根据传入的格式返回格式化后的日期
     */
    public static String addFewDays(Date date, int dayNumber, SimpleDateFormat format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayNumber);
        return format.format(calendar.getTime());
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:59
     * @return java.lang.String
     * @description 将传入的时间根据传入的格式转换为日期字符串
     */
    public static String dateToString(Date date, SimpleDateFormat format) {
        return format.format(date);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 15:59
     * @return java.lang.String
     * @description 将传入的时间根据传入的格式转换为日期字符串
     */
    public static Date StringToDate(String dateString, SimpleDateFormat format) throws Exception {
        return format.parse(dateString);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:02
     * @return java.util.Date
     * @description 获取今天的零点时间
     */
    public static Date getZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:02
     * @return java.util.Date
     * @description 获取某一天的零点时间
     */
    public static Date getZeroTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date returnDate = calendar.getTime();
        return returnDate;
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:02
     * @return java.util.Date
     * @description 获取今天的零点时间，根据传入的格式返回格式化后的日期
     */
    public static String getZeroTime(SimpleDateFormat format) {
        Date returnDate = getZeroTime();
        return format.format(returnDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:02
     * @return java.util.Date
     * @description 获取某一天的零点时间，根据传入的格式返回格式化后的日期
     */
    public static String getZeroTime(Date date, SimpleDateFormat format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date returnDate = calendar.getTime();
        return format.format(returnDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:03
     * @return java.util.Date
     * @description 获取今天的前一天的零点时间
     */
    public static Date getYesterdayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:03
     * @return java.util.Date
     * @description 获取某一天的前一天的零点时间
     */
    public static Date getYesterdayZeroTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date returnDate = calendar.getTime();
        return returnDate;
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:03
     * @return java.util.Date
     * @description 获取今天的前一天的零点时间，根据传入的格式格式时间
     */
    public static String getYesterdayZeroTime(SimpleDateFormat format) {
        Date returnDate = getYesterdayZeroTime();
        return format.format(returnDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:03
     * @return java.util.Date
     * @description 获取某一天的前一天的零点时间，根据传入的格式格式时间
     */
    public static String getYesterdayZeroTime(Date date, SimpleDateFormat format) {
        Date returnDate = getYesterdayZeroTime(date);
        return format.format(returnDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:57
     * @return java.util.Date
     * @description 获取今天的后一天的零点时间
     */
    public static Date getTomorrowZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:57
     * @return java.util.Date
     * @description 获取某一天的后一天的零点时间
     */
    public static Date getTomorrowZeroTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:57
     * @return java.util.Date
     * @description 获取今天的后一天的零点时间
     */
    public static String getTomorrowZeroTime(SimpleDateFormat format) {
        Date returnDate = getTomorrowZeroTime();
        return format.format(returnDate);
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:57
     * @return java.util.Date
     * @description 获取某一天的后一天的零点时间
     */
    public static String getTomorrowZeroTime(Date date, SimpleDateFormat format) {
        Date returnDate = getTomorrowZeroTime(date);
        return format.format(returnDate);
    }

    /**
     * 计算两次时间的间隔，单位为秒
     */
    public static long compareDateSecond(Date d1, Date d2) {
        return Math.abs((d1.getTime() - d2.getTime()) / 1000);
    }

    /**
     * 计算两次时间的间隔，单位天
     */
    public static Integer compareDateDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return Math.abs(c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * @author linxiunan
     * @date 2019/8/22 16:00
     * @return java.lang.Long
     * @description 计算现在到第二天零点的秒数
     */
    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
