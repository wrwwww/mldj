package org.ml.mldj.driver.service.impl;

import org.ml.mldj.driver.service.IDriverInfoService;
import org.ml.mldj.model.driver.entity.DriverInfo;
import org.ml.mldj.driver.mapper.DriverInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class DriverInfoServiceImpl extends ServiceImpl<DriverInfoMapper, DriverInfo> implements IDriverInfoService {

}
