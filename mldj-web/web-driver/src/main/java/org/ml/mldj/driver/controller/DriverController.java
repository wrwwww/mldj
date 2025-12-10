package org.ml.mldj.driver.controller;

import org.ml.mldj.driver.service.DriverService;
import org.ml.mldj.model.dto.DriverLoginForm;
import org.mldj.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("")
    Result<?> login(@RequestBody DriverLoginForm form){
        // 校验关键信息
        driverService.login(form);
        return null;
    }
}
