package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysLoginLogQuery;
import org.ml.mldj.model.system.entity.SysLoginLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-system")
public interface SysLoginLogFeignClient {
    @Operation(summary = "获取分页列表")
    @PostMapping("/sysLoginLog")
    Result<PageVO<SysLoginLog>> findPage(@RequestBody PageQuery<SysLoginLogQuery> sysLoginLogQuery);

    @Operation(summary = "记录登录日志")
    @PostMapping("/sysLoginLog/recordLoginLog")
    Result<?> recordLoginLog(@RequestBody SysLoginLog sysLoginLog);

    @Operation(summary = "获取")
    @GetMapping("/sysLoginLog/getById/{id}")
    Result<SysLoginLog> getById(@PathVariable("id") Long id);
}
