package org.ml.mldj.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-order")
public interface OrderApi {

}
