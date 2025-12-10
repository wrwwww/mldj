package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.Role;
import org.ml.mldj.model.mapper.RoleMapper;
import org.ml.mldj.model.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
