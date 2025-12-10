package org.ml.mldj.driver.client;

import org.mldj.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Driver;

@FeignClient("service-driver")
public interface DriverFeignClient {
    @GetMapping("/driver/{openid}")
    Result<Driver> getDriver(@PathVariable("openid") String openid);
}
