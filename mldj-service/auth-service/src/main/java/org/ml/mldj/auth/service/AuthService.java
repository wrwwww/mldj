package org.ml.mldj.auth.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.ml.mldj.auth.mapper.SysUserMapper;
import org.ml.mldj.model.auth.LoginRequest;
import org.ml.mldj.model.auth.LoginResponse;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.MenuTreeVO;
import org.ml.mldj.security.JwtTokenUtil;
import org.ml.mldj.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    SysUserMapper sysUserMapper;

    public AuthService(UserDetailsService uds,
                       PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = uds;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public LoginResponse login(LoginRequest req) {

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(req.getUsername());

        if (!passwordEncoder.matches(req.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) userDetails;

        String token = jwtTokenUtil.generateAccessToken(loginUser);
        return
                LoginResponse.builder().accessToken(token)
                        .tokenType("Bearer")
                        .expiresIn(jwtTokenUtil.getExpiration())
                        .username(userDetails.getUsername())
//                    .authorities(authentication.getAuthorities())
                        .build();
    }

    public Map<String, Object> userInfo(Long userId) {
        List<SysRole> sysRoles = sysUserMapper.querySysRoleByUserId(userId);
        List<Long> list = sysRoles.stream().map(SysRole::getId).toList();
        List<SysMenu> menuList = sysUserMapper.queryMenuByRoles(list);
        List<MenuTreeVO> menuTreeVOS = buildMenuTree(menuList);
        HashMap<@Nullable String, @Nullable Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("roles", sysRoles);
        objectObjectHashMap.put("menus", menuTreeVOS);
        objectObjectHashMap.put("codes", Lists.newArrayList());
        return objectObjectHashMap;
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
                .sort(menu.getSort())
                .perms(menu.getPerms())
                .menuType(menu.getType())
                .meta(MenuTreeVO.Meta.builder().hideInMenu(menu.getHideInMenu())
                        .icon(menu.getIcon())
                        .titleI18nKey(menu.getTitleI18nKey())
                        .build())
                .build();
    }

    public List<SysMenu> list(Long userId) {
        List<SysRole> sysRoles = sysUserMapper.querySysRoleByUserId(userId);
        List<Long> list = sysRoles.stream().map(SysRole::getId).toList();
        return sysUserMapper.queryMenuByRoles(list);
    }
}
