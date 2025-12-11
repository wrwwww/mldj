package org.ml.mldj.system.client;

import org.ml.mldj.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-system")
public interface SystemFeignClient {
    /**
     * 文本审核
     * @param content
     * @return
     */
    @PostMapping("/ci/textAuditing")
    Result<String> textAuditing(@RequestBody String content);
}
