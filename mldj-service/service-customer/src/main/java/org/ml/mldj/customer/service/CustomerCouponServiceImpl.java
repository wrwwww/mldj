package org.ml.mldj.customer.service;

import org.ml.mldj.model.customer.entity.CustomerCoupon;
import org.ml.mldj.customer.mapper.CustomerCouponMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户优惠券表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CustomerCouponServiceImpl extends ServiceImpl<CustomerCouponMapper, CustomerCoupon> implements ICustomerCouponService {

}
