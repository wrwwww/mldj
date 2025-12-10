package org.ml.mldj.driver.service.impl;

import org.ml.mldj.model.entity.DriverLockdown;
import org.ml.mldj.model.mapper.DriverLockdownMapper;
import org.ml.mldj.model.service.IDriverLockdownService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机禁闭表（禁止接单） 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-10
 */
@Service
public class DriverLockdownServiceImpl extends ServiceImpl<DriverLockdownMapper, DriverLockdown> implements IDriverLockdownService {

}
