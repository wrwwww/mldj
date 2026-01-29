package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysDept;
import org.ml.mldj.system.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysDept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Operation(summary = "获取全部部门节点")
    @GetMapping("/findNodes")
    public Result<List<SysDept>> findNodes() {
        return Result.success(sysDeptService.findDeptTree());
    }

    @Operation(summary = "获取用户部门节点")
    @GetMapping("/findUserNodes")
    public Result<List<SysDept>> findUserNodes() {
        // 目前未区分用户部门，先返回全部
        List<SysDept> list = sysDeptService.list();
        return Result.success(list);
    }

    @Operation(summary = "更新状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        boolean ok = sysDeptService.updateStatus(id, status);
        return ok ? Result.success() : Result.fail();
    }

    @Operation(summary = "获取")
    @GetMapping("/getById/{id}")
    public Result<SysDept> getById(@PathVariable("id") Long id) {
        SysDept dept = sysDeptService.getById(id);
        return Result.success(dept);
    }

    @Operation(summary = "新增")
    @PostMapping("/save")
    public Result<?> save(@RequestBody SysDept sysDept) {
        boolean ok = sysDeptService.save(sysDept);
        return ok ? Result.success() : Result.fail();
    }

    @Operation(summary = "修改")
    @PutMapping("/update")
    public Result<?> update(@RequestBody SysDept sysDept) {
        boolean ok = sysDeptService.updateById(sysDept);
        return ok ? Result.success() : Result.fail();
    }

    @Operation(summary = "删除")
    @DeleteMapping("/remove/{id}")
    public Result<?> remove(@PathVariable("id") Long id) {
        boolean ok = sysDeptService.removeById(id);
        return ok ? Result.success() : Result.fail();
    }
}
