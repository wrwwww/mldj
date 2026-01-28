package org.ml.mldj.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.customer.dto.SendNewOrderMessageForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-mq")
public interface MqFeignClient {
    @Operation(summary = "发送新订单消息（异步）")
    @PostMapping("/message/order/new/sendNewOrderMessageAsync")
    Result<?> sendNewOrderMessageAsync(@RequestBody SendNewOrderMessageForm form);
}
