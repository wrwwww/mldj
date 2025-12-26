package org.ml.mldj.driver.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.driver.service.impl.DriverLocationService;
import org.ml.mldj.model.customer.dto.BefittingDriversForm;
import org.ml.mldj.model.map.dto.NearbyDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class DriverLocationController {

    @Autowired
    DriverLocationService driverLocationService;

    @PutMapping("")
    @Operation(description = "更新司机位置")
    public void update(@RequestParam String driverId, @RequestParam double longitude, @RequestParam double latitude) {
        driverLocationService.updateDriverLocation(driverId, longitude, latitude);
    }

    @GetMapping("")
    @Operation(description = "查找附近的司机")
    Result<List<NearbyDriver>> findNearbyDriversWithFilter(@RequestBody BefittingDriversForm befittingDriversForm) {
        double lon = Double.parseDouble(befittingDriversForm.getStartPlaceLongitude());
        double lat = Double.parseDouble(befittingDriversForm.getStartPlaceLatitude());
        return driverLocationService.findNearbyDrivers(lon, lat, 30.0, 30);
    }
}
