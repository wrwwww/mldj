package org.ml.mldj.map.client;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gaode-client", url = "https://restapi.amap.com/")
public interface GaodeFeignClient {
    @Operation(summary = "高德接口转发")
    @GetMapping("{url}")
    String geo(
            @PathVariable("url") String url
    );
}
