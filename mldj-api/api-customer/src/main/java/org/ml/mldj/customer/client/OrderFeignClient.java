package org.ml.mldj.customer.client;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.customer.dto.CreateNewOrderForm;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-customer")
public interface OrderFeignClient {

    @PostMapping("")
    @Operation(summary = "创建新订单")
    Result<?> createNewOrder(@RequestBody @Valid CreateNewOrderForm form);

    @GetMapping("/{orderId}")
    @Operation(summary = "查询订单状态")
    Result<?> query(@PathVariable("orderId") @Valid @NotNull String orderId);

    @GetMapping("/status/{orderId}")
    @Operation(summary = "查询订单状态")
    Result<?> queryOrderStatus(@PathVariable("orderId") @Valid @NotNull String orderId);

    @DeleteMapping("/{orderId}")
    @Operation(summary = "关闭没有司机接单的订单")
    Result<?> delOrder(@PathVariable("orderId") @Valid @NotNull String orderId);

    @GetMapping("/hasCustomerCurrentOrder")
    @Operation(summary = "查询乘客是否存在当前的订单")
    Result<?> hasCustomerCurrentOrder();

    @GetMapping("/snatching/order")
    @Operation(description = "司机抢单")
    Result<?> snatchingOrder(@RequestParam("driverId") String driverId, @RequestParam("orderId") String orderId);

    @GetMapping("/page")
    @Operation(summary = "订单分页查询")
    Result<?> searchOrderByPage(@RequestBody PageQuery<OrderInfo> query);

    @GetMapping("/searchOrderComprehensiveInfo")
    @Operation(summary = "查询订单综合信息")
    Result<?> searchOrderComprehensiveInfo(@RequestParam("orderId") String orderId);

    @GetMapping("/searchOrderLastGps")
    @Operation(summary = "查询订单最后定位")
    Result<?> searchOrderLastGps(@RequestParam("orderId") String orderId);

    @PostMapping("/close")
    @Operation(summary = "关闭订单")
    Result<?> close(@RequestParam("orderId") String orderId);

    @GetMapping("/getOrderBill")
    @Operation(summary = "获取订单账单")
    Result<?> getOrderBill(@RequestParam("orderId") String orderId);
}
