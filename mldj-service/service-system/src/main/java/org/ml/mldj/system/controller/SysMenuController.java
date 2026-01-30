package org.ml.mldj.system.controller;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.ml.mldj.model.system.vo.MenuTreeVO;
import org.ml.mldj.system.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    @PostMapping("/doAssign")
    public Result<?> doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.success();
    }

    @DeleteMapping("/remove/{id}")
    public Result<?> remove(@PathVariable("id") Long id) {
        boolean ok = sysMenuService.removeById(id);
        return Result.success();
    }

    @PostMapping("/save")
    public Result<?> save(@RequestBody SysMenu sysMenu) {
        boolean ok = sysMenuService.save(sysMenu);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody SysMenu sysMenu) {
        boolean ok = sysMenuService.updateById(sysMenu);
        return Result.success();
    }

    @GetMapping("/findNodes")
    public List<SysMenu> findNodes() {
        return sysMenuService.list();
    }

    @GetMapping("/toAssign/{roleId}")
    public Result<?> toAssign(@PathVariable("roleId") Long roleId) {
        return Result.success(sysMenuService.toAssign(roleId));
    }

    /**
     * 根据角色IDS查询菜单并构建菜单树（给内部服务用）
     */
    @GetMapping("/roles")
    public Result<List<MenuTreeVO>> queryMenuByRoles(@RequestParam("roleIds") List<Long> roleIds) {
        List<SysMenu> menus = sysMenuService.queryMenuByRoles(roleIds);
        List<MenuTreeVO> tree = sysMenuService.buildMenuTree(menus);
        return Result.success(tree);
    }
}
