package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.User;
import org.ml.mldj.model.mapper.UserMapper;
import org.ml.mldj.model.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理系统用户表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
