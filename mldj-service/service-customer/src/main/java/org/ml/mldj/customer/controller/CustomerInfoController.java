package org.ml.mldj.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.customer.service.CustomerInfoService;
import org.ml.mldj.model.customer.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Controller
@RequestMapping("/customerInfo")
public class CustomerInfoController {
    @Autowired
    CustomerInfoService customerService;

    @GetMapping("/{customerId}")
    @Operation(description = "获取用户信息")
    public Result<CustomerVO> queryCustomer(@PathVariable("customerId") @Valid @NotNull(message = "参数不能为空") String customerId) {
        return Result.success(customerService.queryCustomer(customerId));
    }

    @PostMapping("/{customerId}")
    public Result<Boolean> updateCustomer(@PathVariable("customerId") @Valid @NotNull(message = "参数不能为空") String customerId) {
        return null;
    }


}
