package org.ml.mldj.mgr.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.service.SysLoginLogService;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.system.dto.SysLoginLogQuery;
import org.ml.mldj.model.system.entity.SysLoginLog;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author qy
 *
 */
@Tag(name = "系统登录日志管理")
@RestController
@RequestMapping(value="/sysLoginLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysLoginLogController {
	
	@Resource
	private SysLoginLogService sysLoginLogService;

	@Operation(summary = "获取分页列表")
	@PostMapping("{page}/{limit}")
	public Result<?> findPage(


			@Parameter(name = "sysLoginLogVo", description = "查询对象", required = false)
		@RequestBody PageQuery<SysLoginLogQuery> sysLoginLogQuery) {
		return Result.success(sysLoginLogService.findPage(sysLoginLogQuery));
	}

	@Operation(summary = "获取")
	@GetMapping("getById/{id}")
	public Result<?> getById(@PathVariable Long id) {
		return  sysLoginLogService.getById(id);
	}

	@Operation(summary = "记录登录日志")
	@PostMapping("recordLoginLog")
	public Result<?> recordLoginLog(@RequestBody SysLoginLog sysLoginLog) {
		return sysLoginLogService.recordLoginLog(sysLoginLog);
	}

}

