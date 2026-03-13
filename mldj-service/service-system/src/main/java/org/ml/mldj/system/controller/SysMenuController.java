package org.ml.mldj.system.controller;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysMenuQuery;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.ml.mldj.model.system.vo.MenuTreeVO;
import org.ml.mldj.system.service.SysMenuService;
import org.springframework.beans.BeanUtils;
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
//    @GetMapping("/roles")
//    public Result<List<MenuTreeVO>> queryMenuByRoles(@RequestParam("roleIds") List<Long> roleIds) {
//        List<SysMenu> menus = sysMenuService.queryMenuByRoles(roleIds);
//        List<MenuTreeVO> tree = sysMenuService.buildMenuTree(menus);
//        return Result.success(tree);
//    }
    @GetMapping("/tree")
    public Result<List<MenuTreeVO>> tree() {
        List<SysMenu> menus = sysMenuService.list();
        List<MenuTreeVO> tree = sysMenuService.buildMenuTree(menus);
        return Result.success(tree);
    }

    /**
     * 分页查询菜单表
     */
    @PostMapping("/page")
    public Result<PageVO<SysMenu>> page(@RequestBody PageQuery<SysMenuQuery> pageQuery) {
        return Result.success(sysMenuService.page(pageQuery));
    }

    /**
     * 菜单表列表
     */
    @GetMapping("/list")
    public Result<List<SysMenu>> list() {
        List<SysMenu> list = sysMenuService.list();
        return Result.success(list);
    }

    /**
     * 根据ID查询菜单表
     */
    @GetMapping("/{id}")
    public Result<SysMenu> getById(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.success(sysMenu);
    }

    /**
     * 新增菜单表
     */
    @PostMapping
    public Result<?> save(@RequestBody MenuTreeVO sysMenu) {
        SysMenu sysMenu1 = new SysMenu();
        BeanUtils.copyProperties(sysMenu, sysMenu1);
        BeanUtils.copyProperties(sysMenu.getMeta(), sysMenu1);
        sysMenuService.save(sysMenu1);
        return Result.success();
    }

    /**
     * 修改菜单表
     */
    @PutMapping
    public Result<?> update(@RequestBody MenuTreeVO sysMenu) {
        SysMenu sysMenu1 = new SysMenu();
        BeanUtils.copyProperties(sysMenu, sysMenu1);
        BeanUtils.copyProperties(sysMenu.getMeta(), sysMenu1);

        sysMenuService.update(sysMenu1);
        return Result.success();
    }

    /**
     * 删除菜单表
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable String id) {
        sysMenuService.delete(id);
        return Result.success();
    }
}
