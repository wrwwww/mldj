package org.ml.mldj.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setStatus(HttpStatus.OK.value()); // 👈 微服务常用：业务 401，HTTP 200
        response.setContentType("application/json;charset=UTF-8");
        Result<?> result = Result.error(ResultCode.SYS_ERROR);
        objectMapper.writeValue(response.getWriter(), result);
    }
}
