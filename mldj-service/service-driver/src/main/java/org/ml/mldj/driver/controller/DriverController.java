package org.ml.mldj.driver.controller;


import org.ml.mldj.driver.service.DriverService;
import org.mldj.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Driver;
import java.sql.DriverManager;

@RestController
public class DriverController {
    @Autowired
    DriverService driverService;

    @GetMapping("/driver/{openid}")
    Result<Driver> getDriver(@PathVariable("openid") String openid) {
        return Result.success(driverService.getDriverByOpenId(openid));
    }
}
