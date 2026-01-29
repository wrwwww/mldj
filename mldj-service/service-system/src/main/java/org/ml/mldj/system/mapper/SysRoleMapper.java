package org.ml.mldj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ml.mldj.model.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> queryRoleByUserId(Long userId);
}
