package org.ml.mldj.map.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.map.service.MapService;
import org.ml.mldj.model.map.dto.EstimateRouteForm;
import org.ml.mldj.model.map.dto.SearchPlaceForm;
import org.ml.mldj.model.order.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.order.vo.OrderMileageAndMinuteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {
    @Autowired
    MapService mapService;

    @Operation(description = "计算订单公里数和分钟数")
    @GetMapping("/")
    public Result<OrderMileageAndMinuteVO> calculateOrderMileageAndMinute(@RequestBody @Valid OrderMileageAndMinuteForm orderMileageAndMinuteForm) {
        return Result.success(mapService.calculateOrderMileageAndMinute(orderMileageAndMinuteForm));
    }

    //    @Operation(description = "")
//    @GetMapping("/")
//    public Result<BefittingDriversVO> searchBefittingDrivers(BefittingDriversForm befittingDriversForm) {
//
//        return null;
//    }
    @GetMapping("/estimateRoute")
    @Operation(description = "规划路线")
    public Result<?> estimateRoute(EstimateRouteForm form) {
        List<EstimateRouteForm.Route> routes = form.getRoutes();
        return Result.success(mapService.estimateRoute(routes));
    }

    @GetMapping("/search/place")
    @Operation(description = "根据用户的输入，搜索相关的地址")
    public Result<Object> mapList(SearchPlaceForm form) {
        return Result.success(mapService.search(form));
    }
}
