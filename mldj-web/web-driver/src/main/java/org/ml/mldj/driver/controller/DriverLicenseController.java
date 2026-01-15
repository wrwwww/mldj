package org.ml.mldj.driver.controller;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.service.DriverAuthService;
import org.ml.mldj.model.driver.dto.DriverLicenseUploadReq;
import org.ml.mldj.model.driver.dto.FaceLivenessResultReq;
import org.ml.mldj.model.driver.dto.FaceLivenessStartResp;
import org.ml.mldj.model.driver.dto.IdCardUploadReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/driver/auth")
@RestController
public class DriverLicenseController {

    @Autowired
    DriverAuthService driverAuthService;

    @PostMapping("/start")
    public Result<Long> start(@RequestParam Long driverId) {
        return Result.success(driverAuthService.startAuth(driverId));
    }

    @PostMapping("/{authId}/id-card")
    public Result<Void> uploadIdCard(
            @PathVariable Long authId,
            @RequestBody IdCardUploadReq req) {

        driverAuthService.uploadIdCard(authId, req);
        return Result.success();
    }
    @PostMapping("/{authId}/driver-license")
    public Result<Void> uploadDriverLicense(
            @PathVariable Long authId,
            @RequestBody DriverLicenseUploadReq req) {

        driverAuthService.uploadDriverLicense(authId, req);
        return Result.success();
    }
    @PostMapping("/{authId}/face/liveness/start")
    public Result<FaceLivenessStartResp> startLiveness(@PathVariable Long authId) {
        return Result.success(driverAuthService.startFaceLiveness(authId));
    }
    @PostMapping("/{authId}/face/liveness/result")
    public Result<Void> faceResult(
            @PathVariable Long authId,
            @RequestBody FaceLivenessResultReq req) {

        driverAuthService.handleFaceResult(authId, req);
        return Result.success();
    }
}



