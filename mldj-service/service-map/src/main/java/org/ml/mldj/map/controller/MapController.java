package org.ml.mldj.map.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.map.service.MapService;
import org.ml.mldj.model.dto.BefittingDriversForm;
import org.ml.mldj.model.dto.OrderMileageAndMinuteForm;
//import org.ml.mldj.model.vo.BefittingDriversVO;
import org.ml.mldj.model.vo.OrderMileageAndMinuteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    MapService mapService;
    @Operation(description = "计算订单公里数和分钟数")
    @GetMapping("/")
    public Result<OrderMileageAndMinuteVO> calculateOrderMileageAndMinute( @RequestBody @Valid OrderMileageAndMinuteForm orderMileageAndMinuteForm) {
        return  Result.success(mapService.calculateOrderMileageAndMinute(orderMileageAndMinuteForm));
    }
//    @Operation(description = "")
//    @GetMapping("/")
//    public Result<BefittingDriversVO> searchBefittingDrivers(BefittingDriversForm befittingDriversForm) {
//
//        return null;
//    }

}
