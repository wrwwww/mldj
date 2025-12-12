package org.ml.mldj.customer.client;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.customer.AddCustomerCarForm;
import org.ml.mldj.model.entity.CustomerCar;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-customer")
public interface CustomerCarFeignClient {
    @PostMapping("")
    @Operation(summary = "添加客户车辆")
    Result<?> add(@RequestBody @Valid AddCustomerCarForm form);

    @GetMapping("/list")
    @Operation(summary = "查询客户车辆列表")
    Result<List<CustomerCar>> queryList();

    @DeleteMapping("/{carId}")
    @Operation(summary = "删除客户车辆")
    Result<Integer> delCustomerCarById(@PathVariable("carId")  String carId);
}
