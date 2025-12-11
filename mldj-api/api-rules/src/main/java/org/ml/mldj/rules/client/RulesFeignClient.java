package org.ml.mldj.rules.client;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.OrderChargeForm;
import org.ml.mldj.model.vo.OrderChargeVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-rules")
public interface RulesFeignClient {

    Result<OrderChargeVO> estimateOrderCharge(OrderChargeForm orderChargeForm);

}
