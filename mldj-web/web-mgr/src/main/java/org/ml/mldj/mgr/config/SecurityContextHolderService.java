package org.ml.mldj.mgr.config;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SecurityContextHolderService {

    // 保存 SecurityContext
    public SecurityContext captureSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    // 恢复 SecurityContext
    public void restoreSecurityContext(SecurityContext context) {
        SecurityContextHolder.setContext(context);
    }

    // 获取当前用户信息
    public UserInfo getCurrentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return UserInfo.fromAuthentication(authentication);
    }
}
