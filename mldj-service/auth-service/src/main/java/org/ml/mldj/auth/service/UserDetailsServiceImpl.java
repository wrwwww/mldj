package org.ml.mldj.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.ml.mldj.auth.mapper.SysUserMapper;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.security.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserMapper userMapper;

    public UserDetailsServiceImpl(SysUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username);
        SysUser user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //        if (!user.get()) {
//            throw new DisabledException("账号已禁用");
//        }
        List<SysRole> sysRoles = userMapper.querySysRoleByUserId(user.getId());
        HashSet<String> set = new HashSet<>();
        List<Long> list = sysRoles.stream().map(e -> {
            set.add("ROLE_"+e.getRoleCode());
            return e.getId();
        }).toList();
        List<SysMenu> menuList = userMapper.queryMenuByRoles(list);
        for (SysMenu sysMenu : menuList) {
            if (sysMenu.getPerms() != null) {
                set.add(sysMenu.getPerms());
            }
        }


        return new LoginUser(
                user.getId(),
                user.getUsername(),
                set,
                user.getPassword()
        );
    }
}
