package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.client.OrderFeignClient;
import org.ml.mldj.model.dto.customer.CreateNewOrderForm;
import org.ml.mldj.model.vo.customer.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class OrderController {

    @Autowired
    OrderFeignClient orderFeignClient;

    @PostMapping("")
    @Operation(summary = "创建新订单")
    public Result<?> createNewOrder(@RequestBody @Valid CreateNewOrderForm form) {
        return orderFeignClient.createNewOrder(form);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "查询订单状态")
    public Result<?> query(@PathVariable("orderId") @Valid @NotNull String orderId) {
        return orderFeignClient.query(orderId);
    }

    @GetMapping("/status/{orderId}")
    @Operation(summary = "查询订单状态")
    public Result<?> queryOrderStatus(@PathVariable("orderId") @Valid @NotNull String orderId) {
        return orderFeignClient.queryOrderStatus(orderId);
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "关闭没有司机接单的订单")
    public Result<?> delOrder(@PathVariable("orderId") @Valid @NotNull String orderId) {
        return orderFeignClient.delOrder(orderId);
    }

    @GetMapping("/hasCustomerCurrentOrder")
    @Operation(summary = "查询乘客是否存在当前的订单")
    public Result<?> hasCustomerCurrentOrder() {
        return orderFeignClient.hasCustomerCurrentOrder();
    }
}
