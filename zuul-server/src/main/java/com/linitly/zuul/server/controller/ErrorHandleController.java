/**
 * @author: linxiunan
 * @date: 2019/10/12 11:35
 * @descrption
 */
package com.linitly.zuul.server.controller;

import com.linitly.service.provider.entity.common.ResponseResult;
import com.linitly.service.provider.enums.GlobalEnum;
import com.linitly.service.provider.exception.CommonException;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandleController implements ErrorController {

    /**
     * 出现异常后进入该方法，手写该方法的处理方式，避免返回给前端是默认错误页面
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public ResponseResult errorHandle() {
        // 自定义抛出的异常，此处接收获取返回异常中的错误吗和信息，需自定义抛出的异常继承commonException,否则返回服务器错误
        RequestContext requestContext = RequestContext.getCurrentContext();
        Throwable causeThrowable = requestContext.getThrowable().getCause();
        if (causeThrowable instanceof CommonException) {
            CommonException exception = (CommonException) causeThrowable;
            return new ResponseResult(exception.getCode(), exception.getMessage());
        } else {
            return new ResponseResult(GlobalEnum.SYSTEM_ERROR);
        }
    }
}
