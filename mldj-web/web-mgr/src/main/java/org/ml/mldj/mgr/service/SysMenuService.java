package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.ml.mldj.model.system.vo.MenuTreeVO;
import org.ml.mldj.system.client.SysMenuFeignClient;
import org.ml.mldj.system.client.SysRoleFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysMenuService {

    @Autowired
    private SysMenuFeignClient sysMenuFeignClient;
    @Autowired
    private SysRoleFeignClient sysRoleFeignClient;


    public Result<?> save(SysMenu sysMenu) {
        return sysMenuFeignClient.save(sysMenu);
    }


    public Result<?> update(SysMenu sysMenu) {
        return sysMenuFeignClient.update(sysMenu);
    }


    public Result<?> remove(Long id) {
        return sysMenuFeignClient.remove(id);
    }


    public List<SysMenu> findNodes() {
        return sysMenuFeignClient.findNodes();
    }


    public Result<?> doAssign(AssginMenuVo assginMenuVo) {
        return sysMenuFeignClient.doAssign(assginMenuVo);
    }


    public Result<?> toAssign(Long roleId) {
        return sysMenuFeignClient.toAssign(roleId);
    }

    public List<SysMenu> getMenuListByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }

        return sysMenuFeignClient.queryMenuByRoles(roleIds).unwrap();
    }

    public List<MenuTreeVO> buildMenuTree(List<SysMenu> menuList) {
        if (CollectionUtils.isEmpty(menuList)) {
            return Collections.emptyList();
        }

        // 1. 找出所有顶级菜单
        List<MenuTreeVO> rootMenus = menuList.stream()
                .filter(menu -> menu.getParentId() == null || menu.getParentId() == 0)
                .map(this::convertToTreeVO)
                .sorted(Comparator.comparing(MenuTreeVO::getSort))
                .collect(Collectors.toList());

        // 2. 递归构建树
        for (MenuTreeVO rootMenu : rootMenus) {
            buildChildren(rootMenu, menuList);
        }

        return rootMenus;
    }

    private void buildChildren(MenuTreeVO parentMenu, List<SysMenu> allMenus) {
        List<MenuTreeVO> children = allMenus.stream()
                .filter(menu -> parentMenu.getId().equals(menu.getParentId()))
                .map(this::convertToTreeVO)
                .sorted(Comparator.comparing(MenuTreeVO::getSort))
                .collect(Collectors.toList());

        if (!children.isEmpty()) {
            parentMenu.setChildren(children);
            // 递归构建孙子菜单
            for (MenuTreeVO child : children) {
                buildChildren(child, allMenus);
            }
        }
    }

    public List<MenuTreeVO> getMenuTreeByUserId(Long userId) {
        // 1. 获取用户角色
        List<SysRole> roles = sysRoleFeignClient.toAssign(userId).unwrap();

        List<Long> roleIds = (List<Long>) roles.stream().map(SysRole::getId).toList();
        // 2. 查询菜单
        List<SysMenu> menuList = getMenuListByRoleIds(roleIds);

        // 3. 构建树
        return buildMenuTree(menuList);
    }

//    public List<String> getPermsByUserId(Long userId) {
//        return sysMenuFeignClient.queryPermsByUserId(userId);
//    }

    private MenuTreeVO convertToTreeVO(SysMenu menu) {
        return MenuTreeVO.builder()
                .id(menu.getId())
                .parentId(menu.getParentId())
                .name(menu.getName())
                .path(menu.getPath())
                .component(menu.getComponent())
                .icon(menu.getIcon())
                .sort(menu.getSort())
                .visible(menu.getIsHide())
                .perms(menu.getPerms())
                .menuType(menu.getType())
                .build();
    }


}
