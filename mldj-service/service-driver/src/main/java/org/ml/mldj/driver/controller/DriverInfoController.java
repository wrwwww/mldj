package org.ml.mldj.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.service.DriverInfoService;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.driver.dto.DriverLoginForm;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 司机信息表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Controller
@RequestMapping("/driverInfo")
public class DriverInfoController {
    @Autowired
    DriverInfoService driverService;

    @GetMapping("/driver/{openid}")
    Result<DriverInfo> getDriverByOpenId(@PathVariable("openid") String openid) {
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
    @PutMapping("/offline/{driverId}")
    @Operation(description = "司机离线")
    Result<?> Offline(@PathVariable("driverId") String driverId){
        driverService.offline(driverId);
        return Result.success();
    }
    @GetMapping("/snatching/order")
    @Operation(description = "司机抢单")
    Result<?> snatchingOrder(String driverId, String orderId){
        return driverService.snatchingOrder(driverId,orderId);
    }

}
