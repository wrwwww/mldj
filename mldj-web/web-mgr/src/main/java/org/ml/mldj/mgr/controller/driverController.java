package org.ml.mldj.mgr.controller;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.client.DriverFeignClient;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.driver.dto.DriverPageForm;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class driverController {
    @Autowired
    DriverFeignClient driverFeignClient;

    /**
     *
     * @return
     */
    @GetMapping("/page")
    public Result<?> searchDriverByPage(PageQuery<DriverPageForm> form) {
        return driverFeignClient.queryDriverPage(form);
    }

    /**
     * 查询司机综合数据
     *
     * @return
     */
    @GetMapping("/info/{driverId}")
    Result<DriverInfo> queryDriverByDriverId(@PathVariable String driverId) {
        return driverFeignClient.queryDriverByDriverId(driverId);
    }


    @PostMapping("/updateDriverRealAuth")
    public Result<?> updateDriverRealAuth() {
        return driverFeignClient.updateDriverRealAuth();
    }
}
