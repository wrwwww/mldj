package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.client.CustomerCarFeignClient;
import org.ml.mldj.model.customer.dto.AddCustomerCarForm;
import org.ml.mldj.model.entity.CustomerCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/car")
public class CustomerCarController {
    @Autowired
    CustomerCarFeignClient customerCarFeignClient;

    @PostMapping
    @Operation(summary = "添加客户车辆")
    public Result<?> add(@RequestBody @Valid AddCustomerCarForm form) {
        return customerCarFeignClient.add(form);
    }

    @GetMapping("/list")
    @Operation(summary = "查询客户车辆列表")
    public Result<List<CustomerCar>> queryList() {
        return customerCarFeignClient.queryList();
    }

    @DeleteMapping("/{carId}")
    @Operation(summary = "删除客户车辆")
    public Result<?> delCustomerCarById(@PathVariable("carId") @Valid @NotNull(message = "参数不能为空") String carId) {
        return customerCarFeignClient.delCustomerCarById(carId);
    }
}
