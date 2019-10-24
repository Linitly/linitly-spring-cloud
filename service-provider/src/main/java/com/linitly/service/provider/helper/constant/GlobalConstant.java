package com.linitly.service.provider.helper.constant;


public interface GlobalConstant {

	/**
	 * 禁用状态禁用时的值
	 */
	int DISABLED = 0;

	/**
	 * 禁用状态禁用时的值
	 */
	int ENABLED = 1;

	/**
	 * 常规错误码
	 */
	int GENERAL_ERROR = 405;

	/**
	 * 手机号码正则表达式
	 */
	String MOBILE_NUMBER_REG = "^(1[3-9])\\d{9}$";

	/**
	 * 手机号格式不正确提示
	 */
	String MOBILE_ERROR = "手机号格式不正确，请重新输入";

	/**
	 * 日志表后缀
	 */
	String LOG_TABLE_SUFFIX = "_log";

    /**
     * id最小限制
     */
	int ID_MIN = 1;

    /**
     * id为空的提示
     */
	String ID_NOTNULL_TIP = "唯一标识不能为空";

    /**
     * id传入错误提示
     */
	String ID_ERROR_TIP = "唯一标识大小不符合限制";

    /**
     * 通用日期格式类型
     */
	String COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	String CONTENT_TYPE = "Content-Type";

	String CONTENT_TYPE_VALUE = "application/json;charset=UTF-8";
}
