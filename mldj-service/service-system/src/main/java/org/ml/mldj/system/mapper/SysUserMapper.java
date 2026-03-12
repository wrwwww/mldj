package org.ml.mldj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
void updateUserRole(Long userId, List<String> roleIds);

    void delUserRole(Long userId);
}
