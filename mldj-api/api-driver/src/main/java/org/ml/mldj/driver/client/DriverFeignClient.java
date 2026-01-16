package org.ml.mldj.driver.client;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.driver.dto.*;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.entity.DriverSet;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("service-driver")
public interface DriverFeignClient {
    @GetMapping("/driver/{openid}")
    Result<DriverInfo> getDriverByOpenId(@PathVariable("openid") String openid);

    @PostMapping("/driver")
    Result<DriverInfo> registerNewDriver(WxLoginDTO form);

    @GetMapping("/driver/info/{driverId}")
    Result<DriverInfo> queryDriverByDriverId(@PathVariable String driverId);

    @GetMapping("/settings/{driverId}")
    Result<DriverSet> queryDriverSettings(@PathVariable String driverId);

    @GetMapping("/page")
    @Operation(description = "分页查询司机信息")
    Result<PageVO<DriverVO>> queryDriverPage(@RequestParam DriverPageForm form);

    @PutMapping("/offline/{driverId}")
    Result<?> Offline(@PathVariable("driverId") String driverId);



    @PostMapping("/driverLicenseInfo")
    @Operation(description = "更新司机的驾驶证信息")
    Result<?> updateDriverLicense(DriverLicenseInfoDTO driverLicenseInfoDTO, String driverId);

    @PutMapping("/realName")
    @Operation(description = "更新用户的实名信息")
    Result<?> updateDriverRealName(@Valid RealnameSubmitDTO submitDTO);

    Result<DriverInfo> getDriverById(Long driverId);

    Result<Void> updateDriverBasicInfo(Long driverId, DriverBasicInfoUpdateForm form);

    @PutMapping("/online/{driverId}")
    @Operation(description = "司机离线")
    Result<?> Online(@PathVariable("driverId") String driverId);


}
