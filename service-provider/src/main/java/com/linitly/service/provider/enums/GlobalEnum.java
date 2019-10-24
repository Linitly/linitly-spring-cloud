package com.linitly.service.provider.enums;

/**
 * 全局通用枚举
 * @author linxiunan
 * @date 2017年10月23日
 */
public enum GlobalEnum {

	SUCCESS(200, "success"),
	SYSTEM_ERROR(500, "服务器开小差，请稍后重试"),
	UN_SUPPORT_REQUEST(502, "不支持的请求类型"),

	NO_PERMISSION(401, "您没有权限访问该内容"),
	UNAUTHORIZED(403, "未认证,token为空"),
	NOT_FOUND(404, "请求地址不存在"),
	TOKEN_VALIDATE_ERROR(412, "登录失效,请重新登录"),
	TOKEN_ANALYSIS_ERROR(413, "token解析失败"),
    CLASS_METHOD_ERROR(414, "反射获取方法失败"),
	TOKEN_VALIDATE_EXPIRATION(417, "您的账号在异地登录,请重新登录"),

	ANNOTATION_GET_ERROR(441, "获取权限注解内容错误"),
	PERMISSION_ANALYSIS_ERROR(442, "权限列表解析失败"),
	LOG_SAVE_ERROR(443, "日志保存错误"),

	FILE_NOT_UPLOAD_ERROR(456, "所需文件没有上传"),
	PARAM_EMPTY_ERROR(457, "所需参数未传入"),
	FILE_TOO_BIG_ERROR(458, "文件上传超过限制"),

	DUPLICATE_KEY_ERROR(461, "数据库操作错误，主键已存在"),
	;

	private Integer code;
	private String message;

	GlobalEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}

