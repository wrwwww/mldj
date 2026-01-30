package org.ml.mldj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ml.mldj.model.system.entity.SysRoleMenu;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    void batchInsert(List<SysRoleMenu> list);

    List<Long> selectMenuIdsByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
