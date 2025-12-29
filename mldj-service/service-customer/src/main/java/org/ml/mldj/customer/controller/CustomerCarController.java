package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.UserContext;
import org.ml.mldj.customer.service.CustomerCarService;
import org.ml.mldj.model.customer.dto.AddCustomerCarForm;
import org.ml.mldj.model.customer.entity.CustomerCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/customer/car")
public class CustomerCarController {

    @Autowired
    CustomerCarService customerCarService;

    @PostMapping("")
    @Operation(summary = "添加客户车辆")
    public Result<?> add(@RequestBody @Valid AddCustomerCarForm form) {
        String customerId = UserContext.getUserId();
        customerCarService.add(form, customerId);
        return Result.success();
    }

    @GetMapping("/list")
    @Operation(summary = "查询客户车辆列表")
    public Result<List<CustomerCar>> queryList() {
        String customerId = "";
        List<CustomerCar> res = customerCarService.queryList(customerId);
        return Result.success(res);
    }

    @DeleteMapping("/{carId}")
    @Operation(summary = "删除客户车辆")
    public Result<Integer> delCustomerCarById(@PathVariable("carId") @Valid @NotNull(message = "参数不能为空") String carId) {
        String customerId = UserContext.getUserId();
        int rows = this.customerCarService.delByIdAndCustomerId(carId, customerId);
        return Result.success(rows);
    }
}
