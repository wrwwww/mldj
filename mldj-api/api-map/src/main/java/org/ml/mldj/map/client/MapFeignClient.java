package org.ml.mldj.map.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.map.dto.EstimateRouteForm;
import org.ml.mldj.model.order.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.order.vo.OrderMileageAndMinuteVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("service-map")
public interface MapFeignClient {

    Result<OrderMileageAndMinuteVO> estimateOrderMileageAndMinute(OrderMileageAndMinuteForm orderMileageAndMinuteForm);

    @GetMapping("/estimateRoute")
    @Operation(description = "规划路线")
    public Result<?> estimateRoute(EstimateRouteForm form);
}
