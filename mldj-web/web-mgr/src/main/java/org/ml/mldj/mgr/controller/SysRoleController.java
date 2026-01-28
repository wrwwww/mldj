package org.ml.mldj.mgr.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.service.SysRoleService;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.system.dto.SysRoleQuery;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Tag(name = "角色管理")
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Operation(summary = "获取全部角色列表")
    @GetMapping("findAll")
    public Result<List<SysRole>> findAll() {
        List<SysRole> roleList = sysRoleService.findAll();
        return Result.success(roleList);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @Operation(summary = "获取分页列表")
    @PostMapping("{page}/{limit}")
    public Result<?> findPage(
            @Parameter(name = "roleQuery", description = "查询对象", required = false)
            @RequestBody PageQuery<SysRoleQuery> roleQuery) {
        return Result.success(sysRoleService.findPage(roleQuery));
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @Operation(summary = "获取")
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return   sysRoleService.getById(id);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @Operation(summary = "新增角色")
    @PostMapping("save")
    public Result<?> save(@RequestBody @Validated SysRole role) {
       return sysRoleService.save(role);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @Operation(summary = "修改角色")
    @PutMapping("update")
    public Result<?> update(@RequestBody SysRole role) {
        return sysRoleService.update(role);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @Operation(summary = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result<?> remove(@PathVariable Long id) {
        return sysRoleService.remove(id);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @Operation(summary = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<?> batchRemove(@RequestBody List<Long> idList) {
        return sysRoleService.batchRemove(idList);
    }

    @Operation(summary = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public Result<?> toAssign(@PathVariable Long userId) {
        return sysRoleService.toAssign(userId);
    }

    @Operation(summary = "根据用户分配角色")
    @PostMapping("/doAssign")
    public Result<?> doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        return sysRoleService.doAssign(assginRoleVo);
    }


}

