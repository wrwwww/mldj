package org.dj.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.dj.mldj.customer.service.CustomerService;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/{customerId}")
    @Operation(description = "获取用户信息")
    public Result<CustomerVO> queryCustomer(@PathVariable("customerId") String customerId) {
        if (customerId == null) {
//            return
        }
        return Result.success(customerService.queryCustomer(customerId));
    }

    @PostMapping("/{customerId}")
    public  Result<Boolean> updateCustomer(@PathVariable("customerId")String customerId){
        return null;
    }


}
