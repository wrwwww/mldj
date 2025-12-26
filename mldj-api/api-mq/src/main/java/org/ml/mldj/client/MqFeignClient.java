package org.ml.mldj.client;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.customer.dto.SendNewOrderMessageForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-mq")
public interface MqFeignClient {
    @PostMapping("/message/order/new/sendNewOrderMessageAsync")
    Result<?> sendNewOrderMessageAsync(SendNewOrderMessageForm form);
}
