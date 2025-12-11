package org.ml.mldj.driver.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.driver.service.IDriverService;
import org.ml.mldj.model.dto.DriverLoginForm;
import org.ml.mldj.model.dto.DriverPageForm;
import org.ml.mldj.model.entity.Driver;
import org.ml.mldj.model.vo.DriverSettingVO;
import org.ml.mldj.model.vo.DriverVO;
import org.ml.mldj.model.vo.PageVO;
import org.ml.mldj.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DriverController {
    @Autowired
    IDriverService driverService;

    @GetMapping("/driver/{openid}")
    Result<Driver> getDriverByOpenId(@PathVariable("openid") String openid) {
        return Result.success(driverService.getDriverByOpenId(openid));
    }

    @PostMapping("/driver")
    Result<String> registerNewDriver(DriverLoginForm form) {
        String userId = driverService.registerNewDriver(form);
        return Result.success(userId);
    }

    @GetMapping("/driver/info/{driverId}")
    Result<DriverVO> queryDriverByDriverId(@PathVariable String driverId) {
        DriverVO query = driverService.query(driverId);
        return Result.success(query);
    }

    @GetMapping("/settings/{driverId}")
    Result<DriverSettingVO> queryDriverSettings(@PathVariable String driverId) {
        DriverSettingVO vo = driverService.queryDriverSetting(driverId);
        return Result.success(vo);
    }

    @GetMapping("/page")
    @Operation(description = "分页查询司机信息")
    Result<PageVO<DriverVO>> queryDriverPage(@RequestParam DriverPageForm form) {
        PageVO<DriverVO> page = driverService.queryDriverPage(form);
        return Result.success(page);
    }

}
