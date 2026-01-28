package org.ml.mldj.mgr.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.client.OrderFeignClient;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderFeignClient orderFeignClient;

    @GetMapping("/searchOrderComprehensiveInfo")
    public Result<?> searchOrderComprehensiveInfo(String orderId) {
        return orderFeignClient.searchOrderComprehensiveInfo(orderId);
    }

    // todo 支持从参数中获取需要排序的字段
    @GetMapping("/searchOrderByPage")
    public Result<?> searchOrderByPage(PageQuery<OrderInfo> query) {
        return orderFeignClient.searchOrderByPage(query);
    }
    @GetMapping("/searchOrderLastGps")
    public Result<?> searchOrderLastGps(String orderId) {
        return orderFeignClient.searchOrderLastGps(orderId);
    }

    @GetMapping("/close")
    public Result<?> close(String orderId) {
        return orderFeignClient.close(orderId);
    }
    @GetMapping("/orderBill")
    public Result<?> getOrderBill(String orderId) {
        return orderFeignClient.getOrderBill(orderId);
    }

}
