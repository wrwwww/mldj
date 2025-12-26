package org.ml.mldj.customer.service;

import org.ml.mldj.model.customer.entity.CustomerLoginLog;
import org.ml.mldj.customer.mapper.CustomerLoginLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户登录日志表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CustomerLoginLogServiceImpl extends ServiceImpl<CustomerLoginLogMapper, CustomerLoginLog> implements ICustomerLoginLogService {

}
