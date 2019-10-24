package com.linitly.service.provider.handle;

import com.linitly.service.provider.entity.common.ResponseResult;
import com.linitly.service.provider.enums.GlobalEnum;
import com.linitly.service.provider.exception.CommonException;
import com.linitly.service.provider.helper.constant.GlobalConstant;
import com.linitly.service.provider.util.valid.BindingResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object defaultExceptionHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        if (e instanceof MissingServletRequestParameterException) {
            return new ResponseResult(GlobalEnum.PARAM_EMPTY_ERROR);
        } else if (e instanceof HttpMessageNotReadableException) {
            return new ResponseResult(GlobalEnum.PARAM_EMPTY_ERROR);
        } else if (e instanceof CommonException) {
            CommonException exception = (CommonException) e;
            return new ResponseResult(exception.getCode(), exception.getMessage());
        } else if (e instanceof MaxUploadSizeExceededException) {
            return new ResponseResult(GlobalEnum.FILE_TOO_BIG_ERROR);
        } else if (e instanceof MissingServletRequestPartException) {
            return new ResponseResult(GlobalEnum.FILE_NOT_UPLOAD_ERROR);
        } else if (e instanceof MultipartException) {
            return new ResponseResult(GlobalEnum.FILE_NOT_UPLOAD_ERROR);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseResult(GlobalEnum.UN_SUPPORT_REQUEST);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return new ResponseResult(GlobalConstant.GENERAL_ERROR, BindingResultUtil.getBindingResultErrMsg(ex.getBindingResult()));
        } else if (e instanceof DuplicateKeyException) {
            // 数据库新增时，唯一限制字段已存在抛错
            return new ResponseResult(GlobalEnum.DUPLICATE_KEY_ERROR);
        }
        return new ResponseResult(GlobalEnum.SYSTEM_ERROR);
    }

}
