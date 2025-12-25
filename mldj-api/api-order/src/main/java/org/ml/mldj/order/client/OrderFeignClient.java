package org.ml.mldj.order.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.PageForm;
import org.ml.mldj.model.dto.customer.OrderForm;
import org.ml.mldj.model.vo.PageVO;
import org.ml.mldj.model.order.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-order")
public interface OrderFeignClient {

    @PostMapping
    @Operation(description = "创建新订单")
    Result<OrderVO> createOrder(OrderForm orderForm);

    Result<OrderVO> query(String orderId, String customerId);

    Result<Integer> del(String orderId, String customerId);

    Result<PageVO<OrderVO>> page(PageForm form, String customerId);
}
