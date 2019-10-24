package com.linitly.service.provider.entity.common;

import com.linitly.service.provider.enums.GlobalEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回对象封装类
 * @author siwei
 * @date 2018年7月26日
 */
@Data
@ApiModel
public class ResponseResult<T> {
	
	@ApiModelProperty(value = "状态码")
	private Integer code;
	
	@ApiModelProperty(value = "提示信息")
	private String message;

	@ApiModelProperty(value = "返回数据")
	private T data;

	@ApiModelProperty(value = "特殊数据")
	private Object specialData;

	public ResponseResult(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	public ResponseResult(Integer code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResponseResult(T data, Object specialData) {
		this.code = GlobalEnum.SUCCESS.getCode();
		this.message = GlobalEnum.SUCCESS.getMessage();
		this.data = data;
		this.specialData = specialData;
	}

	public ResponseResult() {
		this.code = GlobalEnum.SUCCESS.getCode();
		this.message = GlobalEnum.SUCCESS.getMessage();
	}

	public ResponseResult(T data) {
		this.code = GlobalEnum.SUCCESS.getCode();
		this.message = GlobalEnum.SUCCESS.getMessage();
		this.data = data;
	}

	public ResponseResult(GlobalEnum exResEnum) {
		this.message = exResEnum.getMessage();
		this.code = exResEnum.getCode();
	}
}
