package org.ml.mldj.common.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ml.mldj.common.utils.UserContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class PerRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        // 从 gateway 传来的头里取 userId
        String userId = request.getHeader("X-User-Id");
        String userType = request.getHeader("X-User-Type");

        if (userId == null || userType == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 校验该服务是否允许此 userType
        if (!"USER".equals(userType)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        // 存 ThreadLocal
        UserContext.setUserId(userId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }
}
