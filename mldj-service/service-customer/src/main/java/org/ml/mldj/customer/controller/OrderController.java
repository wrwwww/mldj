package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.service.OrderService;
import org.ml.mldj.model.common.PageRequest;
import org.ml.mldj.model.customer.dto.CreateNewOrderForm;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("")
    @Operation(summary = "创建新订单")
    public Result<?> createNewOrder(@RequestBody @Valid CreateNewOrderForm form) {
        // todo 提取用户id
        String customerId = "";
        form.setCustomerId(customerId);
        HashMap result = orderService.createNewOrder(form);
        return Result.success(result);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "查询订单状态")
    public Result<?> query(@PathVariable("orderId") @Valid @NotNull String orderId) {
        String customerId = "";
        OrderVO query = orderService.query(orderId, customerId);
        return Result.success(query);
    }

    @GetMapping("/status/{orderId}")
    @Operation(summary = "查询订单状态")
    public Result<?> queryOrderStatus(@PathVariable("orderId") @Valid @NotNull String orderId) {
        String customerId = "";
        OrderVO query = orderService.query(orderId, customerId);
        return Result.success(query.getStatus());
    }

    @DeleteMapping("/{orderId}")
    @Operation(summary = "关闭没有司机接单的订单")
    public Result<?> delOrder(@PathVariable("orderId") @Valid @NotNull String orderId) {
        String customerId = "";

        int result = orderService.delOrder(orderId, customerId);
        return Result.success(result);
    }

    @GetMapping("/hasCustomerCurrentOrder")
    @Operation(summary = "查询乘客是否存在当前的订单")
    public Result<?> hasCustomerCurrentOrder() {
        String customerId = "";
        OrderVO result = orderService.hasCustomerCurrentOrder(customerId);
        return Result.success(result);
    }


    @PostMapping("/confirmArriveStartPlace")
    @Operation(summary = "确定司机已经到达")
    public Result<?> confirmArriveStartPlace(@RequestBody @Valid ConfirmArriveStartPlaceForm form) {
        boolean result = this.orderService.confirmArriveStartPlace(form);
        return Result.success(result);
    }

    @PostMapping("/searchOrderForMoveById")

    @Operation(summary = "查询订单信息用于司乘同显功能")
    public Result<?> searchOrderForMoveById(@RequestBody @Valid SearchOrderForMoveByIdForm form) {
        String customerId = "";
        HashMap map = orderService.searchOrderForMoveById(form);
        return R.ok()
                .put("result", map);
    }

    @PostMapping("/createWxPayment")
    @Operation(summary = "创建支付订单")
    public Result<?> createWxPayment(@RequestBody @Valid CreateWxPaymentForm form) {
        String customerId = "";
        HashMap map = this.orderService.createWxPayment(form.getOrderId(), form.getCustomerId(), form.getVoucherId(), form.getVoucherId());
        return R.ok()
                .put("result", map);
    }


    @PostMapping("/updateOrderAboutPayment")
    @Operation(summary = "查询司机是否关联某订单")

    public Result<?> updateOrderAboutPayment(@RequestBody @Valid UpdateOrderAboutPaymentForm form) {
        String result = this.orderService.updateOrderAboutPayment(form);
        return R.ok()
                .put("result", result);
    }

    @GetMapping("/page")
    @Operation(summary = "查询订单分页记录")
    public Result<?> page(@RequestParam @Valid PageRequest form) {
        String customerId = "";
        PageVO<OrderVO> page = orderService.page(form, customerId);
        return Result.success(page);
    }

    @PostMapping("/searchOrderById")
    @Operation(summary = "根据ID查询订单信息")
    public Result<?> searchOrderById(@RequestBody @Valid SearchOrderByIdForm form) {
        String customerId = "";
        form.setCustomerId(customerId);
        HashMap map = this.orderService.searchOrderById(form);
        return R.ok()
                .put("result", map);
    }
}
