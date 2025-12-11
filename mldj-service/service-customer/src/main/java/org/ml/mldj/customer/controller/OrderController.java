package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.service.OrderService;
import org.ml.mldj.model.dto.customer.CreateNewOrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/createNewOrder")
    @Operation(summary = "创建新订单")
    public Result<?> createNewOrder(@RequestBody @Valid CreateNewOrderForm form) {
        // todo 提取用户id
        String customerId = "";
        form.setCustomerId(customerId);
        HashMap result = orderService.createNewOrder(form);
        return Result.success(result);
    }

    @PostMapping("/searchOrderStatus")
    @Operation(summary = "查询订单状态")
    public R searchOrderStatus(@RequestBody @Valid SearchOrderStatusForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        Integer status = this.orderService.searchOrderStatus(form);
        return R.ok()
                .put("result", status);
    }

    @PostMapping("/deleteUnAcceptOrder")
    @Operation(summary = "关闭没有司机接单的订单")
    @SaCheckLogin
    public R deleteUnAcceptOrder(@RequestBody @Valid DeleteUnAcceptOrderForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);

        String result = this.orderService.deleteUnAcceptOrder(form);
        return R.ok()
                .put("result", result);
    }

    @PostMapping("/hasCustomerCurrentOrder")
    @SaCheckLogin
    @Operation(summary = "查询乘客是否存在当前的订单")
    public R hasCustomerCurrentOrder() {
        long customerId = StpUtil.getLoginIdAsLong();
        HasCustomerCurrentOrderForm form = new HasCustomerCurrentOrderForm();
        form.setCustomerId(customerId);
        HashMap map = this.orderService.hasCustomerCurrentOrder(form);
        return R.ok()
                .put("result", map);
    }


    @PostMapping("/confirmArriveStartPlace")
    @SaCheckLogin
    @Operation(summary = "确定司机已经到达")
    public R confirmArriveStartPlace(@RequestBody @Valid ConfirmArriveStartPlaceForm form) {
        boolean result = this.orderService.confirmArriveStartPlace(form);
        return R.ok()
                .put("result", result);
    }

    @PostMapping("/searchOrderForMoveById")
    @SaCheckLogin
    @Operation(summary = "查询订单信息用于司乘同显功能")
    public R searchOrderForMoveById(@RequestBody @Valid SearchOrderForMoveByIdForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        HashMap map = this.orderService.searchOrderForMoveById(form);
        return R.ok()
                .put("result", map);
    }

    @PostMapping("/createWxPayment")
    @Operation(summary = "创建支付订单")
    @SaCheckLogin
    public R createWxPayment(@RequestBody @Valid CreateWxPaymentForm form) {
        Long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        HashMap map = this.orderService.createWxPayment(form.getOrderId(), form.getCustomerId(), form.getVoucherId(), form.getVoucherId());
        return R.ok()
                .put("result", map);
    }


    @PostMapping("/updateOrderAboutPayment")
    @Operation(summary = "查询司机是否关联某订单")
    @SaCheckLogin
    public R updateOrderAboutPayment(@RequestBody @Valid UpdateOrderAboutPaymentForm form) {
        String result = this.orderService.updateOrderAboutPayment(form);
        return R.ok()
                .put("result", result);
    }

    @PostMapping("/searchCustomerOrderByPage")
    @SaCheckLogin
    @Operation(summary = "查询订单分页记录")
    public R searchCustomerOrderByPage(@RequestBody @Valid SearchCustomerOrderByPageForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        PageUtils pageUtils = this.orderService.searchCustomerOrderByPage(form);
        return R.ok()
                .put("result", pageUtils);
    }

    @PostMapping("/searchOrderById")
    @SaCheckLogin
    @Operation(summary = "根据ID查询订单信息")
    public R searchOrderById(@RequestBody @Valid SearchOrderByIdForm form) {
        long customerId = StpUtil.getLoginIdAsLong();
        form.setCustomerId(customerId);
        HashMap map = this.orderService.searchOrderById(form);
        return R.ok()
                .put("result", map);
    }
}
