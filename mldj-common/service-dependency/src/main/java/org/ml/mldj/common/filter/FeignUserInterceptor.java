package org.ml.mldj.common.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.ml.mldj.common.utils.UserContext;
import org.springframework.stereotype.Component;

@Component
public class FeignUserInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String userId = UserContext.getUserId();
        if (userId != null) {
            template.header("X-User-Id", userId);
        }
    }
}
