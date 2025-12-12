package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.UserContext;
import org.ml.mldj.customer.client.CustomerFeignClient;
import org.ml.mldj.model.dto.customer.UpdateCustomerForm;
import org.ml.mldj.model.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController("/customer")
public class CustomerController {

    @Autowired
    CustomerFeignClient customerFeignClient;

    @GetMapping
    @Operation(description = "获取用户信息")
    Result<CustomerVO> queryCustomer() {
        String customerId = UserContext.getUserId();
        return customerFeignClient.queryCustomer(customerId);
    }

    @PostMapping
    Result<Boolean> updateCustomer(@RequestBody @Valid UpdateCustomerForm form) {
        String customerId = UserContext.getUserId();
        form.setCustomerId(customerId);
        return customerFeignClient.updateCustomer(form, customerId);
    }

}
