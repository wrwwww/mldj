package org.ml.mldj.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.customer.dto.OrderForm;
import org.ml.mldj.model.order.dto.OrderPageForm;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.ml.mldj.model.order.vo.OrderVO;
import org.ml.mldj.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderService;

    @GetMapping("/{orderId}")
    @Operation(description = "查询订单详情")
    public Result<?> query(@PathVariable("orderId") String orderId) {
        return Result.success(orderService.query(orderId));
    }

    @GetMapping("/page")
    @Operation(description = "订单分页")
    public Result<PageVO<OrderVO>> page(@RequestBody @Valid OrderPageForm pageForm) {
        return Result.success(orderService.page(pageForm));
    }

    @PostMapping("")
    @Operation(description = "创建新订单")
    public Result<OrderVO> createOrder(@RequestBody @Valid OrderForm form) {
        return Result.success(orderService.add(form));
    }
    @GetMapping("/")
    @Operation(description ="订单支付完成")
    Result<?> orderPaymentSuccess(String orderNo){
       return Result.success(orderService.paymentSuccess(orderNo));
    }
    @GetMapping("/snatching/order")
    @Operation(description = "司机抢单")
    Result<?> snatchingOrder(String driverId, String orderId) {
        return orderService.snatchingOrder(driverId, orderId);
    }
    @GetMapping("/admin/page")
    Result<?> searchOrderByPage(PageQuery<OrderInfo> query){
        return Result.success(orderService.page(query));
    }
}
