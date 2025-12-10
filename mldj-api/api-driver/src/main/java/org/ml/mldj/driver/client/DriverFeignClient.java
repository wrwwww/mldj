package org.ml.mldj.driver.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.model.dto.DriverLoginForm;
import org.ml.mldj.model.dto.DriverPageForm;
import org.ml.mldj.model.entity.Driver;
import org.ml.mldj.model.vo.DriverSettingVO;
import org.ml.mldj.model.vo.DriverVO;
import org.ml.mldj.model.vo.PageVO;
import org.mldj.common.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("service-driver")
public interface DriverFeignClient {
    @GetMapping("/driver/{openid}")
    Result<Driver> getDriverByOpenId(@PathVariable("openid") String openid);

    @PostMapping("/driver")
    Result<String> registerNewDriver(DriverLoginForm form);

    @GetMapping("/driver/info/{driverId}")
    Result<DriverVO> queryDriverByDriverId(@PathVariable String driverId);

    @GetMapping("/settings/{driverId}")
    Result<DriverSettingVO> queryDriverSettings(@PathVariable String driverId);

    @GetMapping("/page")
    @Operation(description = "分页查询司机信息")
    Result<PageVO<DriverVO>> queryDriverPage(@RequestParam DriverPageForm form);

}
