package org.ml.mldj.order.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.customer.dto.OrderForm;
import org.ml.mldj.model.order.dto.OrderPageForm;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.order.vo.OrderVO;
import org.ml.mldj.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;

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
    public Result<?> add(@RequestBody @Valid OrderForm form) {
        return Result.success(orderService.add(form));
    }
}
