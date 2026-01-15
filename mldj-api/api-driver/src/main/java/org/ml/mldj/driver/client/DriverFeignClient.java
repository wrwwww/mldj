package org.ml.mldj.driver.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.model.driver.dto.DriverBasicInfoUpdateForm;
import org.ml.mldj.model.driver.dto.DriverLoginForm;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.driver.dto.DriverWorkStatusUpdateForm;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.model.driver.vo.DriverSettingVO;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.driver.vo.DriverVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("service-driver")
public interface DriverFeignClient {
    @GetMapping("/driver/{openid}")
    Result<DriverInfo> getDriverByOpenId(@PathVariable("openid") String openid);

    @PostMapping("/driver")
    Result<String> registerNewDriver(DriverLoginForm form);

    @GetMapping("/driver/info/{driverId}")
    Result<DriverVO> queryDriverByDriverId(@PathVariable String driverId);

    @GetMapping("/settings/{driverId}")
    Result<DriverSettingVO> queryDriverSettings(@PathVariable String driverId);

    @GetMapping("/page")
    @Operation(description = "分页查询司机信息")
    Result<PageVO<DriverVO>> queryDriverPage(@RequestParam DriverPageForm form);

    @PutMapping("/offline/{driverId}")
    Result<?> Offline(@PathVariable("driverId") String driverId);

    @GetMapping("/snatching/order")
    @Operation(description = "司机抢单")
    Result<?> snatchingOrder(String driverId, String orderId);

    Result<DriverInfo> getDriverById(Long driverId);

    Result<Void> updateDriverBasicInfo(Long driverId, DriverBasicInfoUpdateForm form);

    Result<Void> updateDriverWorkStatus(Long driverId, DriverWorkStatusUpdateForm form);
}
