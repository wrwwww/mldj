package org.ml.mldj.customer.client;


import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.model.customer.dto.UpdateCustomerForm;
import org.ml.mldj.model.customer.vo.CustomerVO;
import org.ml.mldj.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-customer")
public interface CustomerFeignClient {

    @GetMapping("/customerInfo/{customerId}")
    @Operation(description = "获取用户信息")
    Result<CustomerVO> queryCustomer(@PathVariable("customerId") String customerId);

    @PostMapping("/customerInfo/")
    @Operation(summary = "更新用户信息")
    Result<Boolean> updateCustomer(@RequestBody UpdateCustomerForm form);
}
