package org.ml.mldj.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.entity.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysRole> querySysRoleByUserId(Long userId);

    List<SysMenu> queryMenuByRoles(@Param("roleIds") List<Long> roleIds);
}
