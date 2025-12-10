package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.Permission;
import org.ml.mldj.model.mapper.PermissionMapper;
import org.ml.mldj.model.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
