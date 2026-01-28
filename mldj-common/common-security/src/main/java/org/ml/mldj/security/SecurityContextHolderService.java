package org.ml.mldj.security;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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
//    public UserInfo getCurrentUser() {
//        Authentication authentication = SecurityContextHolder
//                .getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
//        }
//        return UserInfo.fromAuthentication(authentication);
//    }
}
