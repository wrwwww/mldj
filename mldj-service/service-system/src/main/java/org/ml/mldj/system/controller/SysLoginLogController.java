package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysLoginLogQuery;
import org.ml.mldj.model.system.entity.SysLoginLog;
import org.ml.mldj.system.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统登录日志表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysLoginLog")
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @Operation(summary = "获取分页列表")
    @PostMapping
    public Result<PageVO<SysLoginLog>> findPage(@RequestBody PageQuery<SysLoginLogQuery> sysLoginLogQuery) {
        PageVO<SysLoginLog> pageVO = sysLoginLogService.page(sysLoginLogQuery);
        return Result.success(pageVO);
    }

    @Operation(summary = "记录登录日志")
    @PostMapping("/recordLoginLog")
    public Result<?> recordLoginLog(@RequestBody SysLoginLog sysLoginLog) {
        boolean ok = sysLoginLogService.save(sysLoginLog);
        return Result.success();
    }

    @Operation(summary = "获取")
    @GetMapping("/getById/{id}")
    public Result<SysLoginLog> getById(@PathVariable("id") Long id) {
        SysLoginLog log = sysLoginLogService.getById(id);
        return Result.success(log);
    }
}
