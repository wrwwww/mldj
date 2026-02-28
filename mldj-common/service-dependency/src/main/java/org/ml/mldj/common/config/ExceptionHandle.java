package org.ml.mldj.common.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.ResultCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
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
    public Result<?> Exception(Exception e, HttpServletRequest request,
                               WebRequest webRequest) {
        log.error(e.getMessage(), e.getCause());
        // 构建失败请求信息
        String errorMessage = String.format("""
            请求失败详情:
            ======================
            时间: %s
            URL: %s
            方法: %s
            客户端IP: %s
            用户代理: %s
            请求参数: %s
            请求体: %s
            错误类型: %s
            错误信息: %s
            堆栈跟踪: %s
            ======================
            """,
                LocalDateTime.now(),
                request.getRequestURL().toString(),
                request.getMethod(),
                getClientIp(request),
                request.getHeader("User-Agent"),
                request.getQueryString(),
                "", // 注意：需要特殊处理才能获取请求体
                e.getClass().getName(),
                e.getMessage(),
                getStackTraceAsString(e)
        );

        // 记录到日志（不同级别）
        log.error("请求失败: URL={}, Method={}, Error={}",
                request.getRequestURL(),
                request.getMethod(),
                e.getMessage());

//        log.error("详细错误信息:\n{}", errorMessage);

        // 返回标准错误响应
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

    /**
     * 获取客户端IP
     */
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取异常堆栈信息
     */
    private String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
