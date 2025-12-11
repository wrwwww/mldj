package org.ml.mldj.map.client;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.BefittingDriversForm;
import org.ml.mldj.model.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.vo.BefittingDriversVO;
import org.ml.mldj.model.vo.OrderMileageAndMinuteVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-map")
public interface MapFeignClient {

    Result<OrderMileageAndMinuteVO> estimateOrderMileageAndMinute(OrderMileageAndMinuteForm orderMileageAndMinuteForm);

    Result<BefittingDriversVO> searchBefittingDrivers(BefittingDriversForm befittingDriversForm);


}
