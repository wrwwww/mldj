package org.ml.mldj.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.service.DriverService;
import org.ml.mldj.model.common.LoginVO;
import org.ml.mldj.model.driver.dto.DriverBasicInfoUpdateForm;
import org.ml.mldj.model.driver.dto.WeChatLoginRequest;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
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
    public Result<LoginVO> login(@RequestBody @Valid WeChatLoginRequest form) {
        // 校验关键信息
        LoginVO login = driverService.login(form);
        return Result.success(login);
    }

    @GetMapping("/driver/{driverId}")
    public Result<?> getDriver(@PathVariable @Valid Long driverId) {
        return driverService.getDriverById(driverId);
    }


    @PutMapping("/driver/{driverId}/basic-info")
    public Result<?> updateDriverBasicInfo(@PathVariable @Valid Long driverId, @RequestBody @Valid DriverBasicInfoUpdateForm form) {
        driverService.updateBasicInfo(driverId, form);
        return Result.success("Driver basic info updated successfully");
    }

    @PutMapping("/offline/{driverId}")
    @Operation(description = "司机离线")
    Result<?> Offline(@PathVariable("driverId") Long driverId) {
        return driverService.offline(driverId);
    }

    @PutMapping("/online/{driverId}")
    @Operation(description = "司机在线")
    Result<?> Online(@PathVariable("driverId") Long driverId) {
        return driverService.online(driverId);
    }

    @GetMapping("/settings/{driverId}")
    Result<DriverSettingVO> queryDriverSettings(@PathVariable Long driverId) {
        DriverSettingVO vo = driverService.queryDriverSetting(driverId);
        return Result.success(vo);
    }

}
