package org.ml.mldj.mgr.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.service.SysDeptService;
import org.ml.mldj.model.system.entity.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "部门管理")
@RestController
@RequestMapping(value="/sysDept")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysDeptController {
	
	@Autowired
	private SysDeptService sysDeptService;

	@Operation(summary = "获取")
	@PreAuthorize("hasAuthority('bnt.sysDept.list')")
	@GetMapping("getById/{id}")
	public Result<?> getById(@PathVariable Long id) {
		return sysDeptService.getById(id);
	}

//	@Log(title = "部门管理", businessType = BusinessType.INSERT)
	@Operation(summary = "新增")
	@PreAuthorize("hasAuthority('bnt.sysDept.add')")
	@PostMapping("save")
	public Result<?> save(@RequestBody SysDept sysDept) {
		sysDeptService.save(sysDept);
		return Result.success();
	}

//	@Log(title = "部门管理", businessType = BusinessType.UPDATE)
	@Operation(summary = "修改")
	@PreAuthorize("hasAuthority('bnt.sysDept.update')")
	@PutMapping("update")
	public Result<?> update(@RequestBody SysDept sysDept) {
		sysDeptService.update(sysDept);
		return Result.success();
	}

//	@Log(title = "部门管理", businessType = BusinessType.DELETE)
	@Operation(summary = "删除")
	@PreAuthorize("hasAuthority('bnt.sysDept.remove')")
	@DeleteMapping("remove/{id}")
	public Result<?> remove(@PathVariable Long id) {
		sysDeptService.remove(id);
		return Result.success();
	}

	@Operation(summary = "获取全部部门节点")
	@PreAuthorize("hasAuthority('bnt.sysDept.list')")
	@GetMapping("findNodes")
	public Result<?> findNodes() {
		return Result.success(sysDeptService.findNodes());
	}

	@Operation(summary = "获取用户部门节点")
	@GetMapping("findUserNodes")
	public Result<?> findUserNodes() {
		return Result.success(sysDeptService.findUserNodes());
	}

//	@Log(title = "部门管理", businessType = BusinessType.STATUS)
	@Operation(summary = "更新状态")
	@GetMapping("updateStatus/{id}/{status}")
	public Result<?> updateStatus(@PathVariable Long id, @PathVariable Integer status) {
		return sysDeptService.updateStatus(id, status);
	}

}

