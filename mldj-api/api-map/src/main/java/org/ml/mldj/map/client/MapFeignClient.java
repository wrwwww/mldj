package org.ml.mldj.map.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.map.dto.EstimateRouteForm;
import org.ml.mldj.model.order.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.order.vo.OrderMileageAndMinuteVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface MapFeignClient {

    @Operation(description = "计算订单公里数和分钟数")
    @GetMapping("/map/")
    Result<OrderMileageAndMinuteVO> estimateOrderMileageAndMinute(@RequestBody OrderMileageAndMinuteForm orderMileageAndMinuteForm);

    @GetMapping("/estimateRoute")
    @Operation(description = "规划路线")
    Result<?> estimateRoute(EstimateRouteForm form);
}
