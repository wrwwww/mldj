package org.ml.mldj.security;

import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {}

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static LoginUser getCurrentUser() {
        Authentication auth = getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new BizException(ResultCode.SYS_ERROR);
        }
        return (LoginUser) auth.getPrincipal();
    }
    public static Long getUserId() {
        return getCurrentUser().getUserId();
    }
}
