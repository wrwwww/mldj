package org.ml.mldj.rules.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.Getter;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.CalOrderFeeForm;
import org.ml.mldj.model.dto.CalculateSplitForm;
import org.ml.mldj.model.vo.CalOrderFeeVO;
import org.ml.mldj.model.vo.CalculateSplitVO;
import org.ml.mldj.rules.service.RulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rules")
public class RulesController {

    @Autowired
    RulesService rulesService;

    /**
     * 计算订单费用
     */
    @Operation(description = "计算订单费用")
    @GetMapping("")
    public Result<CalOrderFeeVO> calculateOrderFee(@RequestBody @Valid CalOrderFeeForm form) {
        return Result.success(rulesService.calculateOrderFee(form));
    }

    @Operation(description = "计算系统分账")
    @GetMapping("/cal")
    public Result<CalculateSplitVO> calculateSplit(@RequestBody @Valid CalculateSplitForm form) {
        return Result.success(rulesService.calculateSplit(form));
    }
}
