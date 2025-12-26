package org.ml.mldj.map.client;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.order.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.order.vo.OrderMileageAndMinuteVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-map")
public interface MapFeignClient {

    Result<OrderMileageAndMinuteVO> estimateOrderMileageAndMinute(OrderMileageAndMinuteForm orderMileageAndMinuteForm);


}
