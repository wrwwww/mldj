package org.ml.mldj.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysMenuQuery;
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

//    /**
//     * 构建菜单树结构
//     */
//    public List<MenuTreeVO> buildMenuTree(List<SysMenu> menuList) {
//        if (menuList == null || menuList.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        // 1. 顶级菜单
//        List<MenuTreeVO> rootMenus = menuList.stream()
//                .filter(menu -> menu.getParentId() == null || menu.getParentId() == 0)
//                .map(this::convertToTreeVO)
//                .sorted(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Integer::compareTo)))
//                .collect(Collectors.toList());
//
//        // 2. 递归子节点
//        for (MenuTreeVO root : rootMenus) {
//            buildChildren(root, menuList);
//        }
//        return rootMenus;
//    }
//
//    private void buildChildren(MenuTreeVO parent, List<SysMenu> allMenus) {
//        List<MenuTreeVO> children = allMenus.stream()
//                .filter(menu -> parent.getId().equals(menu.getParentId()))
//                .map(this::convertToTreeVO)
//                .sorted(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Integer::compareTo)))
//                .collect(Collectors.toList());
//
//        if (!children.isEmpty()) {
//            parent.setChildren(children);
//            for (MenuTreeVO child : children) {
//                buildChildren(child, allMenus);
//            }
//        }
//    }

//    private MenuTreeVO convertToTreeVO(SysMenu menu) {
//        return MenuTreeVO.builder()
//                .id(menu.getId())
//                .parentId(menu.getParentId())
//                .name(menu.getName())
//                .path(menu.getPath())
//                .component(menu.getComponent())
//                .icon(menu.getIcon())
//                .sort(menu.getSort())
//                .visible(menu.getIsHide())
//                .perms(menu.getPerms())
//                .menuType(menu.getType())
//                .build();
//    }
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
//                .sorted(Comparator.comparing(MenuTreeVO::getMeta::, Comparator.nullsLast(Integer::compareTo)))
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
//                .sorted(Comparator.comparing(MenuTreeVO::getSort, Comparator.nullsLast(Integer::compareTo)))
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
                .path(menu.getPath())
                .component(menu.getComponent())
                .perms(menu.getPerms())
                .menuType(menu.getType())
                .meta(MenuTreeVO.Meta.builder().hideInMenu(menu.getHideInMenu())
                        .icon(menu.getIcon())
                        .sort(menu.getSort())
                        .title(menu.getTitle())
                        .titleI18nKey(menu.getTitleI18nKey())
                        .build())
                .build();
    }


    /**
     * 分页查询角色表
     */
    public PageVO<SysMenu> page(PageQuery
                                        <SysMenuQuery> pageQuery) {
        Page<SysMenu> pageInfo = pageQuery.toPage();
        // 构建查询条件
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        SysMenuQuery filters = pageQuery.getFilters();


        if(filters.getName()!=null){
            queryWrapper.like("name",filters.getName());
        }


        if(filters.getType()!=null){
            queryWrapper.like("type",filters.getType());
        }


        if(filters.getPath()!=null){
            queryWrapper.like("path",filters.getPath());
        }






        if(filters.getStatus()!=null){
            queryWrapper.like("status",filters.getStatus());
        }


        if(filters.getActive_menu()!=null){
            queryWrapper.like("active_menu",filters.getActive_menu());
        }




        // 构建排序条件
        if (pageQuery.hasSort()) {
            pageQuery.getSorts().forEach(e -> {
                String column = e.getUnderlineColumn();
                if (e.isAscending()) {
                    queryWrapper.orderByAsc(column);
                } else {
                    queryWrapper.orderByDesc(column);
                }
            });
        }
        // 分页查询
        Page<SysMenu> result = sysMenuMapper.selectPage(pageInfo, queryWrapper);
        return PageVO.buildPageVO(result);
    }

    /**
     * 根据ID查询菜单表
     */
    public SysMenu getById(String id) {
        return sysMenuMapper.selectById(id);
    }

    /**
     * 新增菜单表
     */
    public Long save(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        return sysMenu.getId();
    }

    /**
     * 修改菜单表
     */
    public void update(SysMenu sysMenu) {
        sysMenuMapper.updateById(sysMenu);
    }

    /**
     * 删除菜单表
     */
    public void delete(String id) {
        sysMenuMapper.deleteById(id);
    }
}
