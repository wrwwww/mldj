package org.ml.mldj.driver.controller;

import jakarta.validation.Valid;
import org.ml.mldj.driver.service.DriverService;
import org.ml.mldj.model.dto.DriverLoginForm;
import org.ml.mldj.model.vo.LoginVO;
import org.mldj.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("")
    public Result<?> login(@RequestBody @Valid DriverLoginForm form) {
        // 校验关键信息
        LoginVO login = driverService.login(form);
        return Result.success(login);
    }

    @GetMapping("/driver")
    public Result<?> query() {

    }

}
