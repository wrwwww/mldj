package org.ml.mldj.map.client;

import org.ml.mldj.model.vo.map.GaodeDrivingResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://restapi.amap.com/")
public interface GaodeFeignClient {
    @GetMapping("{url}")
    String geo(
            @PathVariable("url") String url
    );
}
