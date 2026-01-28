package org.ml.mldj.driver.client;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.driver.dto.*;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.entity.DriverSet;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("service-driver")
public interface DriverFeignClient {
    @GetMapping("/driverInfo/driver/{openid}")
    @Operation(description = "根据 openid 获取司机信息")
    Result<DriverInfo> getDriverByOpenId(@PathVariable("openid") String openid);

    @PostMapping("/driverInfo/driver")
    @Operation(description = "注册新司机")
    Result<DriverInfo> registerNewDriver(@RequestBody WxLoginDTO form);

    @GetMapping("/driverInfo/driver/info/{driverId}")
    @Operation(description = "根据司机ID查询司机信息")
    Result<DriverInfo> queryDriverByDriverId(@PathVariable String driverId);

    @GetMapping("/driverInfo/settings/{driverId}")
    @Operation(description = "查询司机设置")
    Result<DriverSet> queryDriverSettings(@PathVariable String driverId);

    @GetMapping("/driverInfo/page")
    @Operation(description = "分页查询司机信息")
    Result<PageVO<DriverVO>> queryDriverPage(@RequestParam PageQuery<DriverPageForm> form);

    @PutMapping("/driverInfo/offline/{driverId}")
    @Operation(description = "司机离线")
    Result<?> Offline(@PathVariable("driverId") String driverId);



    @PostMapping("/driverInfo/driverLicenseInfo")
    @Operation(description = "更新司机的驾驶证信息")
    Result<?> updateDriverLicense(@RequestBody DriverLicenseInfoDTO driverLicenseInfoDTO, @RequestParam("driverId") String driverId);

    @PutMapping("/driverInfo/realName")
    @Operation(description = "更新用户的实名信息")
    Result<?> updateDriverRealName(@Valid RealnameSubmitDTO submitDTO);

    @GetMapping("/driverInfo/getById/{driverId}")
    @Operation(description = "根据ID获取司机信息")
    Result<DriverInfo> getDriverById(@PathVariable("driverId") Long driverId);

    @PutMapping("/driverInfo/basic/{driverId}")
    @Operation(description = "更新司机基础信息")
    Result<Void> updateDriverBasicInfo(@PathVariable("driverId") Long driverId, @RequestBody DriverBasicInfoUpdateForm form);

    @PutMapping("/driverInfo/online/{driverId}")
    @Operation(description = "司机离线")
    Result<?> Online(@PathVariable("driverId") String driverId);




    @GetMapping("/searchDriverComprehensiveData")
    @Operation(description = "查询司机综合数据")
    Result<?> searchDriverComprehensiveData();


    @PostMapping("/updateDriverRealAuth")
    @Operation(description = "更新司机实名认证状态")
    Result<?> updateDriverRealAuth();
}
