package org.ml.mldj.customer.service;

import org.ml.mldj.model.customer.entity.CustomerCar;
import org.ml.mldj.model.mldj.mapper.CustomerCarMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户车辆信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CustomerCarServiceImpl extends ServiceImpl<CustomerCarMapper, CustomerCar> implements ICustomerCarService {

}
