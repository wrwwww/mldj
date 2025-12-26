package org.ml.mldj.driver.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.customer.dto.BefittingDriversForm;
import org.ml.mldj.model.map.dto.NearbyDriver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-driver")
public interface DriverLocationFeignClient {
    @PutMapping("")
    Result<?> update(@RequestParam String driverId, @RequestParam double longitude, @RequestParam double latitude);
    @GetMapping("")
    @Operation(description = "查找附近的司机")
    Result<List<NearbyDriver>> findNearbyDriversWithFilter(BefittingDriversForm befittingDriversForm);

}
