package org.ml.mldj.driver.controller;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.client.OrderFeignClient;
import org.ml.mldj.driver.client.DriverFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class OrderController {

    @Autowired
    OrderFeignClient orderFeignClient ;
    @GetMapping("")
    public Result<?> snatchingOrder(){
        String driverId="";
        String orderId="";
        return orderFeignClient.snatchingOrder(driverId,orderId);
    }
}
