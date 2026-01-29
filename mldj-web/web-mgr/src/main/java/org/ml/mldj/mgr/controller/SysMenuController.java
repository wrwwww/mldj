package org.ml.mldj.mgr.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.service.SysMenuService;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.ml.mldj.model.system.vo.MenuTreeVO;
import org.ml.mldj.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "菜单管理")
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @PreAuthorize("hasAuthority('bnt.sysMenu.list')")
    @Operation(summary = "获取菜单")
    @GetMapping("findNodes")
    public Result<?> findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.success(list);
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.add')")
    @Operation(summary = "新增菜单")
    @PostMapping("save")
    public Result<?> save(@RequestBody SysMenu permission) {
        return sysMenuService.save(permission);
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.update')")
    @Operation(summary = "修改菜单")
    @PutMapping("update")
    public Result<?> update(@RequestBody SysMenu permission) {
        return sysMenuService.update(permission);
    }

    @PreAuthorize("hasAuthority('bnt.sysMenu.remove')")
    @Operation(summary = "删除菜单")
    @DeleteMapping("remove/{id}")
    public Result<?> remove(@PathVariable Long id) {
        return sysMenuService.remove(id);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.assignAuth')")
    @Operation(summary = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public Result<?> toAssign(@PathVariable Long roleId) {
        return sysMenuService.toAssign(roleId);
    }

    @PreAuthorize("hasAuthority('bnt.sysRole.assignAuth')")
    @Operation(summary = "给角色分配权限")
    @PostMapping("/doAssign")
    public Result<?> doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        return sysMenuService.doAssign(assginMenuVo);
    }

    @GetMapping("/tree")
    public Result<List<MenuTreeVO>> menuTree(){
        Long userId = SecurityUtils.getUserId();
        return Result.success(sysMenuService.getMenuTreeByUserId(userId));
    }

}

