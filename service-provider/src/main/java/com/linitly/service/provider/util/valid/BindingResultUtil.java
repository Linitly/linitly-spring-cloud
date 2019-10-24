package com.linitly.service.provider.util.valid;

import com.linitly.service.provider.entity.common.ResponseResult;
import com.linitly.service.provider.helper.constant.GlobalConstant;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @author siwei
 * @Description 获取验证消息工具类
 * @date 2018年8月24日
 */
public class BindingResultUtil {

    public static String getBindingResultErrMsg(BindingResult bindingResult) {
        StringBuffer errorMsg = new StringBuffer();
        bindingResult.getAllErrors().forEach(e -> {
            if (e instanceof FieldError) {
                FieldError fe = (FieldError) e;
                // 返回验证信息去除 field显示
//				errorMsg.append(fe.getField()).append(fe.getDefaultMessage()).append(";");
                errorMsg.append(fe.getDefaultMessage()).append(";");
                return;
            }
            errorMsg.append(e.getDefaultMessage()).append(";");
        });
        return errorMsg.toString();
    }

    /**
     * 根据校验结果类返回统一返回封装类(controller层校验数据有错误信息时)
     *
     * @param bindingResult 校验结果类
     * @return
     */
    public static ResponseResult paramError(BindingResult bindingResult) {
        ResponseResult responseResult = new ResponseResult(GlobalConstant.GENERAL_ERROR, BindingResultUtil.getBindingResultErrMsg(bindingResult));
        return responseResult;
    }
}
