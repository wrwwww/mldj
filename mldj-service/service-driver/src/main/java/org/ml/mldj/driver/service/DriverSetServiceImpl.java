package org.ml.mldj.driver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.driver.entity.DriverSet;
import org.ml.mldj.driver.mapper.DriverSetMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机设置表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class DriverSetServiceImpl extends ServiceImpl<DriverSetMapper, DriverSet> implements IService<DriverSet> {

}
