package org.ml.mldj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ml.mldj.model.system.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void deleteByUserId(Long userId);

    void batchInsert(List<SysUserRole> list);
}
