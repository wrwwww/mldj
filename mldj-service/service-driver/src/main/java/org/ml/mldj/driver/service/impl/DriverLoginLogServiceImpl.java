package org.ml.mldj.driver.service.impl;

import org.ml.mldj.driver.service.IDriverLoginLogService;
import org.ml.mldj.model.driver.entity.DriverLoginLog;
import org.ml.mldj.driver.mapper.DriverLoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机登录日志表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class DriverLoginLogServiceImpl extends ServiceImpl<DriverLoginLogMapper, DriverLoginLog> implements IDriverLoginLogService {

}
