package org.ml.mldj.order.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.customer.dto.OrderForm;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.order.dto.OrderPageForm;
import org.ml.mldj.model.order.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-order")
public interface OrderFeignClient {

    @PostMapping("/orderInfo")
    @Operation(description = "创建新订单")
    Result<OrderVO> createOrder(@RequestBody OrderForm orderForm);

    @GetMapping("/orderInfo/{orderId}")
    @Operation(description = "查询订单详情")
    Result<OrderVO> query(@PathVariable("orderId") String orderId);

    @DeleteMapping("/orderInfo/{orderId}")
    @Operation(description = "删除/取消订单")
    Result<Integer> del(@PathVariable("orderId") String orderId, @RequestParam(value = "customerId", required = false) String customerId);

    @GetMapping("/orderInfo/page")
    @Operation(description = "订单分页")
    Result<PageVO<OrderVO>> page(@RequestBody OrderPageForm form, @RequestParam(value = "customerId", required = false) String customerId);

    @GetMapping("/")
    @Operation(description ="订单支付完成")
    Result<?> orderPaymentSuccess(String orderNo);
}
