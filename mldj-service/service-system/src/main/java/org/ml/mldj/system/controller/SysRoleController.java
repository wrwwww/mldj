package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysRoleQuery;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginRoleVo;
import org.ml.mldj.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;

    @Operation(summary = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Result<List<SysRole>> toAssign(@PathVariable("userId") Long userId) {
        List<SysRole> list = sysRoleService.toAssign(userId);
        return Result.success(list);
    }

    @Operation(summary = "获取")
    @GetMapping("/getById/{id}")
    public Result<SysRole> getById(@PathVariable("id") Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.success(role);
    }

    @Operation(summary = "新增角色")
    @PostMapping("/save")
    public Result<?> save(@RequestBody SysRole sysRole) {
        boolean ok = sysRoleService.save(sysRole);
        return Result.success();
    }

    @Operation(summary = "修改角色")
    @PutMapping("/update")
    public Result<?> update(@RequestBody SysRole sysRole) {
        boolean ok = sysRoleService.updateById(sysRole);
        return Result.success();
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/remove/{id}")
    public Result<?> remove(@PathVariable("id") Long id) {
        boolean ok = sysRoleService.removeById(id);
        return Result.success();
    }

    @Operation(summary = "获取分页列表")
    @PostMapping
    public Result<PageVO<SysRole>> findPage(@RequestBody PageQuery<SysRoleQuery> roleQuery) {
        PageVO<SysRole> pageVO = sysRoleService.page(roleQuery);
        return Result.success(pageVO);
    }

    @Operation(summary = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result<?> batchRemove(@RequestBody List<Long> idList) {
        boolean ok = sysRoleService.removeBatchByIds(idList);
        return Result.success();
    }

    @Operation(summary = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result<?> doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        // 目前 VO 为空，这里仅占位，返回成功
        sysRoleService.doAssign(assginRoleVo);
        return Result.success();
    }

    @Operation(summary = "获取全部角色列表")
    @GetMapping("/findAll")
    public List<SysRole> findAll() {
        return sysRoleService.list();
    }

}
