package org.ml.mldj.rules.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.CalOrderFeeForm;
import org.ml.mldj.model.vo.CalOrderFeeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-rules")
public interface RulesFeignClient {

    /**
     * 计算订单费用
     */
    @Operation(description = "计算订单费用")
    @GetMapping("")
    Result<CalOrderFeeVO> calculateOrderFee(@RequestBody CalOrderFeeForm form);

}
