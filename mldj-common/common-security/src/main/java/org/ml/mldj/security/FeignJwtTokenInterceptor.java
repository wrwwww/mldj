package org.ml.mldj.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignJwtTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return;
        }

        Object credentials = authentication.getCredentials();
        if (credentials instanceof String token && !token.isBlank()) {

            template.header(
                    "Authorization",
                    "Bearer " + token
            );
        }
    }
}
