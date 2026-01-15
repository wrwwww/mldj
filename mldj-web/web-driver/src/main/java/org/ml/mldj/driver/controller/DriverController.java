package org.ml.mldj.driver.controller;

import jakarta.validation.Valid;
import org.ml.mldj.driver.service.DriverService;
import org.ml.mldj.model.driver.dto.DriverBasicInfoUpdateForm;
import org.ml.mldj.model.driver.dto.DriverLoginForm;
import org.ml.mldj.model.common.LoginVO;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.driver.dto.DriverRegistrationForm;
import org.ml.mldj.model.driver.dto.DriverWorkStatusUpdateForm;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 司机接口
 * 司机登录，存在用户直接登录，不存在就自动注册
 * 司机人脸识别信息上传
 * 司机身份信息上传
 */
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

    @GetMapping("/driver/{driverId}")
    public Result<?> getDriver(@PathVariable @Valid Long driverId) {
        DriverVO driver = driverService.getDriverById(driverId);
        return Result.success(driver);
    }

    @PostMapping("/register")
    public Result<?> registerDriver(@RequestBody @Valid DriverRegistrationForm form) {
        driverService.register(form);
        return Result.success("Driver registered successfully");
    }

    @PutMapping("/driver/{driverId}/basic-info")
    public Result<?> updateDriverBasicInfo(@PathVariable @Valid Long driverId, @RequestBody @Valid DriverBasicInfoUpdateForm form) {
        driverService.updateBasicInfo(driverId, form);
        return Result.success("Driver basic info updated successfully");
    }

    @PutMapping("/driver/{driverId}/work-status")
    public Result<?> updateDriverWorkStatus(@PathVariable @Valid Long driverId, @RequestBody @Valid DriverWorkStatusUpdateForm form) {
        driverService.updateWorkStatus(driverId, form);
        return Result.success("Driver work status updated successfully");
    }

}
