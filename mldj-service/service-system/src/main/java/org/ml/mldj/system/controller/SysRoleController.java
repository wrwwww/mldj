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
@RequestMapping("/system/role")
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



    @Operation(summary = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result<?> batchRemove(@RequestBody List<Long> idList) {
         sysRoleService.removeBatchByIds(idList);
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
    @GetMapping("/list")
    public List<SysRole> list() {
        return sysRoleService.list();
    }

    /**
     * 分页查询角色表
     */
    @PostMapping("/page")
    public Result<PageVO<SysRole>> page(@RequestBody PageQuery<SysRoleQuery> pageQuery) {

        return Result.success(sysRoleService.page(pageQuery));
    }


    /**
     * 根据ID查询角色表
     */
    @GetMapping("/{id}")
    public Result<SysRole> getById(@PathVariable String id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.success(sysRole);
    }

    /**
     * 新增角色表
     */
    @PostMapping
    public Result<Long> save(@RequestBody SysRole sysRole) {
        Long id = sysRoleService.save(sysRole);
        return Result.success(id);
    }

    /**
     * 修改角色表
     */
    @PutMapping
    public Result<?> update(@RequestBody SysRole sysRole) {
        sysRoleService.update(sysRole);
        return Result.success();
    }

    /**
     * 删除角色表
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable String id) {
        sysRoleService.delete(id);
        return Result.success();
    }

    @GetMapping("/menuIds/{roleId}")
    public Result<?> queryMenuIdsByRoleId(@PathVariable("roleId") String roleId){
        return Result.success(sysRoleService.menuIds(roleId));
    }



}
