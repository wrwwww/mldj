package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysOperLogQuery;
import org.ml.mldj.model.system.entity.SysOperLog;
import org.ml.mldj.system.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统操作日志表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysOperLog")
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    @Operation(summary = "获取分页列表")
    @PostMapping
    public Result<PageVO<SysOperLog>> findPage(@RequestBody PageQuery<SysOperLogQuery> sysOperLogQuery) {
        PageVO<SysOperLog> pageVO = sysOperLogService.page(sysOperLogQuery);
        return Result.success(pageVO);
    }

    @Operation(summary = "获取")
    @GetMapping("/getById/{id}")
    public Result<SysOperLog> getById(@PathVariable("id") Long id) {
        SysOperLog log = sysOperLogService.getById(id);
        return Result.success(log);
    }

    @Operation(summary = "记录日志")
    @PostMapping("/saveSysLog")
    public Result<?> saveSysLog(@RequestBody SysOperLog sysOperLog) {
        boolean ok = sysOperLogService.save(sysOperLog);
        return Result.success();
    }
}
