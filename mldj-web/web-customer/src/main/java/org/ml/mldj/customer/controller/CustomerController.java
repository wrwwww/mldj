package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.UserContext;
import org.ml.mldj.customer.client.CustomerFeignClient;
import org.ml.mldj.model.customer.dto.UpdateCustomerForm;
import org.ml.mldj.model.customer.vo.CustomerVO;
import org.ml.mldj.security.SecurityUtils;
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
        Long userId = SecurityUtils.getUserId();
        return customerFeignClient.queryCustomer(userId);
    }

    @PostMapping
    Result<Boolean> updateCustomer(@RequestBody @Valid UpdateCustomerForm form) {
        Long userId = SecurityUtils.getUserId();
        form.setCustomerId(userId);
        return customerFeignClient.updateCustomer(form);
    }

}
