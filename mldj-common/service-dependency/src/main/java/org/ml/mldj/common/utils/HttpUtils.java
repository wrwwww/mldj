package org.ml.mldj.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

public class HttpUtils {
    public static String buildUrl(String baseUrl, String path, Object queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .path(path);

        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(queryParams);
        Arrays.stream(wrapper.getPropertyDescriptors()).iterator().forEachRemaining(pd -> {
            if (!"class".equals(pd.getName())) {
                Object value = wrapper.getPropertyValue(pd.getName());
                if (value != null) {
                    builder.queryParam(pd.getName(), value);
                }
            }
        });

        return builder.toUriString();
    }
}
