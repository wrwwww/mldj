package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysOperLogQuery;
import org.ml.mldj.model.system.entity.SysOperLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-system")
public interface SysOperLogFeignClient {
    @Operation(summary = "获取分页列表")
    @PostMapping("/sysOperLog")
    Result<PageVO<SysOperLog>> findPage(@RequestBody PageQuery<SysOperLogQuery> sysOperLogQuery);

    @Operation(summary = "获取")
    @GetMapping("/sysOperLog/getById/{id}")
    Result<SysOperLog> getById(@PathVariable("id") Long id);

    @Operation(summary = "记录日志")
    @PostMapping("/sysOperLog/saveSysLog")
    Result<?> saveSysLog(@RequestBody SysOperLog sysOperLog);
}
