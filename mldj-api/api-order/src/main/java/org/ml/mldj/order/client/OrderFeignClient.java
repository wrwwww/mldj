package org.ml.mldj.order.client;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.customer.OrderForm;
import org.ml.mldj.model.vo.customer.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-order")
public interface OrderFeignClient {

    Result<OrderVO> createOrder(OrderForm orderForm);
}
