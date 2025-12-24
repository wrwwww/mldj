package org.ml.mldj.map.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(url = "https://restapi.amap.com/")
public interface GaodeFeignClient {
    @GetMapping("/v3/geocode/geo")
    Map<String, Object> geo(
            @RequestParam("address") String address,
            @RequestParam("key") String key
    );
}
