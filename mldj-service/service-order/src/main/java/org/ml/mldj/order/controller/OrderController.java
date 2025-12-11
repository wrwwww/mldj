package org.ml.mldj.order.controller;


import org.ml.mldj.order.service.OrderService;
import org.ml.mldj.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public Result<?> hello() {
        return Result.success("hello");
    }
}
