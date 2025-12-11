package org.ml.mldj.common.config;

import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.ResultCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(BizException.class)
    public Result<?> BizExceptionHandle(BizException e) {
        log.error(e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<?> Exception(Exception e) {
        log.error(e.getMessage(), e.getCause());
        return Result.error(ResultCode.SYS_ERROR);
    }

    /**
     * 处理参数校验错误的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handle(MethodArgumentNotValidException ex) {
        String msg =
                Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        return Result.error(ResultCode.PARAM_LOST.getCode(), msg);
    }
}
