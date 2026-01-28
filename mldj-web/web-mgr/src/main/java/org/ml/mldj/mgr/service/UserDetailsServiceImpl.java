package org.ml.mldj.mgr.service;

import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.config.LoginUser;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.system.client.SysUserFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // 这里可以注入你的用户服务，从数据库或远程服务获取用户信息
    @Autowired
    SysUserFeignClient sysUserFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // 实际项目中应该从数据库或用户服务获取
//        Result<SysUser> result = sysUserFeignClient.queryByUsername(username);
//        SysUser user = result.unwrap();
//        return User.withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles("ADMIN")
//                .build();
//        // 这里简化示例
        if ("admin".equals(username)) {

            return LoginUser
                    .builder().username(username)
                    .password("$2a$10$xCEDjBN/6vLhRMGboTMJu.P1so9dZRWuX0Dk3mQ.8VPsBHUjk4GJK") // BCrypt 加密后的密码
                    .userId("1")
                    .build();
        } else {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
    }
}