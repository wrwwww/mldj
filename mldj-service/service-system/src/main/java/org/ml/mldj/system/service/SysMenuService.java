package org.ml.mldj.system.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.ml.mldj.model.system.vo.MenuTreeVO;
import org.ml.mldj.system.mapper.SysMenuMapper;
import org.ml.mldj.system.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    public List<SysMenu> queryMenuByRoles(List<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }
        return sysMenuMapper.queryMenuByRoles(roleIds);
    }

    public boolean save(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu) > 0;
    }

    public boolean updateById(SysMenu sysMenu) {
        return sysMenuMapper.updateById(sysMenu) > 0;
    }

    public boolean removeById(Long id) {
        return sysMenuMapper.deleteById(id) > 0;
    }

    public List<SysMenu> list() {
        return sysMenuMapper.selectList(null);
    }

    /**
     * 给角色分配菜单（权限）
     */
    public void doAssign(AssginMenuVo assginMenuVo) {
        Long roleId = assginMenuVo.getRoleId();
        List<Long> menuIdList = assginMenuVo.getMenuIdList();

        // 1. 先删除该角色原有的菜单关联
        sysRoleMenuMapper.deleteByRoleId(roleId);

        // 2. 如果有新的菜单，批量插入
        if (menuIdList != null && !menuIdList.isEmpty()) {
            List<org.ml.mldj.model.system.entity.SysRoleMenu> list = menuIdList.stream().map(menuId -> {
                org.ml.mldj.model.system.entity.SysRoleMenu rm = new org.ml.mldj.model.system.entity.SysRoleMenu();
                rm.setRoleId(roleId);
                rm.setMenuId(menuId);
                return rm;
            }).collect(Collectors.toList());
            if (!list.isEmpty()) {
                sysRoleMenuMapper.batchInsert(list);
            }
        }
    }

    /**
     * 根据角色获取已分配的菜单ID列表
     */
    public List<Long> toAssign(Long roleId) {
        return sysRoleMenuMapper.selectMenuIdsByRoleId(roleId);
    }

    /**
     * 构建菜单树结构
     */
    public List<MenuTreeVO> buildMenuTree(List<SysMenu> menuList) {
        if (menuList == null || menuList.isEmpty()) {
            return Collections.emptyList();
        }

        // 1. 顶级菜单
        List<MenuTreeVO> rootMenus = menuList.stream()
                .filter(menu -> menu.getParentId() == null || menu.getParentId() == 0)
                .map(this::convertToTreeVO)
                .sorted(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());

        // 2. 递归子节点
        for (MenuTreeVO root : rootMenus) {
            buildChildren(root, menuList);
        }
        return rootMenus;
    }

    private void buildChildren(MenuTreeVO parent, List<SysMenu> allMenus) {
        List<MenuTreeVO> children = allMenus.stream()
                .filter(menu -> parent.getId().equals(menu.getParentId()))
                .map(this::convertToTreeVO)
                .sorted(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());

        if (!children.isEmpty()) {
            parent.setChildren(children);
            for (MenuTreeVO child : children) {
                buildChildren(child, allMenus);
            }
        }
    }

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
