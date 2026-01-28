package org.ml.mldj.mgr.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.service.SysOperLogService;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.system.dto.SysOperLogQuery;
import org.ml.mldj.model.system.entity.SysOperLog;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author qy
 *
 */
@Tag(name = "系统操作日志管理")
@RestController
@RequestMapping(value="/sysOperLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysOperLogController {
	
	@Resource
	private SysOperLogService sysOperLogService;

	@Operation(summary = "获取分页列表")
	@PostMapping("{page}/{limit}")
	public Result<?> findPage(


			@Parameter(name = "sysOperLogVo", description = "查询对象", required = false)
		@RequestBody PageQuery<SysOperLogQuery> sysOperLogQuery) {
		return Result.success(sysOperLogService.findPage(sysOperLogQuery));
	}

	@Operation(summary = "获取")
	@GetMapping("getById/{id}")
	public Result<?> getById(@PathVariable Long id) {
		return sysOperLogService.getById(id);
	}

	@Operation(summary = "记录日志")
	@PostMapping("saveSysLog")
	public Result<?> saveSysLog(@RequestBody SysOperLog sysOperLog) {
//		sysOperLog.setOperName(AuthContextHolder.getUserId()+"");
		return sysOperLogService.saveSysLog(sysOperLog);
	}

}

