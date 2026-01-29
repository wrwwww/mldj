package org.ml.mldj.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ml.mldj.model.system.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> queryMenuByRoles(List<Long> roleIds);
}
