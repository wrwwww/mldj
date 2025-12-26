package org.ml.mldj.driver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.driver.entity.DriverAccount;
import org.ml.mldj.driver.mapper.DriverAccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机账户表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class DriverAccountServiceImpl extends ServiceImpl<DriverAccountMapper, DriverAccount> implements IService<DriverAccount> {

}
