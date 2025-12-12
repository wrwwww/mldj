package org.ml.mldj.customer.client;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.model.dto.customer.UpdateCustomerForm;
import org.ml.mldj.model.vo.CustomerVO;
import org.ml.mldj.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-customer")
public interface CustomerFeignClient {

    @GetMapping("/{customerId}")
    @Operation(description = "获取用户信息")
    Result<CustomerVO> queryCustomer(@PathVariable("customerId") String customerId);

    @PostMapping("/{customerId}")
    Result<Boolean> updateCustomer(@RequestBody UpdateCustomerForm form, @PathVariable("customerId") String customerId);
}
