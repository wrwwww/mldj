package org.ml.mldj.common.config;

import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.Result;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(BizException.class)
    public Result<?> BizExceptionHandle(BizException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}
