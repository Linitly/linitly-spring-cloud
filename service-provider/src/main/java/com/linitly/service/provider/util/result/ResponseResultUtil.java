package com.linitly.service.provider.util.result;

import com.github.pagehelper.PageInfo;
import com.linitly.service.provider.entity.common.PageResponseResult;
import com.linitly.service.provider.entity.common.ResponseResult;
import com.linitly.service.provider.enums.GlobalEnum;
import com.linitly.service.provider.exception.CommonException;
import com.linitly.service.provider.helper.constant.GlobalConstant;

import java.util.List;

/**
 * @Description 返回结果工具类
 * @author linxiunan
 * @date 2019年2月27日
 */
public class ResponseResultUtil {

	/**
	 * 通过框架分页信息类和结果集封装成统一分页返回封装类
	 * @param pageInfo pageHelper框架中分页信息类
	 */
	public static <T> PageResponseResult copyToPageResponseResult(PageInfo<T> pageInfo) {
		if (pageInfo == null) {
			return null;
		}
		PageResponseResult pageResponseResult = new PageResponseResult(pageInfo.getTotal(), pageInfo.getPages(), 
				pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.isIsFirstPage(), pageInfo.isIsLastPage(), 
				pageInfo.isHasPreviousPage(), pageInfo.isHasNextPage(), pageInfo.getList());
		return pageResponseResult;
	}
	
	/**
	 * 通过结果list返回统一返回封装类
	 * @param list 查询的结果list
	 */
	public static <T> ResponseResult getResponseResult(List<T> list) {
		PageInfo<T> pageInfo = new PageInfo<>(list);
		PageResponseResult pageResponseResult = copyToPageResponseResult(pageInfo);
		return pageResponseResult == null ? new ResponseResult(new PageResponseResult(0, null)) : new ResponseResult(pageResponseResult);
	}

	/**
	 * 通过结果list返回统一返回封装类
	 * @param list 查询的结果list
	 * @param specialData 特殊数据
	 */
	public static <T> ResponseResult getResponseResult(List<T> list, Object specialData) {
		PageInfo<T> pageInfo = new PageInfo<>(list);
		PageResponseResult pageResponseResult = copyToPageResponseResult(pageInfo);
		return pageResponseResult == null ? new ResponseResult(new PageResponseResult(0, null)) : new ResponseResult(pageResponseResult, specialData);
	}
	
	/**
	 * 返回错误码为405的统一返回封装类
	 * @param message 错误提示
	 */
	public static ResponseResult generalError(String message) {
		ResponseResult responseResult = new ResponseResult(GlobalConstant.GENERAL_ERROR, message);
		return responseResult;
	}

	/**
	 * @author linxiunan
	 * @date 2019/10/8 10:26
	 * @description 微服务调用返回判断返回结果，如果错误直接返回错误信息
	 */
	public static void checkResult(ResponseResult result) {
		if (!result.getCode().equals(GlobalEnum.SUCCESS.getCode())) {
			throw new CommonException(result.getCode(), result.getMessage());
		}
	}
}
