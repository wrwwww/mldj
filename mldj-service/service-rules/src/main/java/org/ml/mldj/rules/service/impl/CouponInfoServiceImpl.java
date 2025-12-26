package org.ml.mldj.rules.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ml.mldj.rules.mapper.CouponInfoMapper;
import org.ml.mldj.model.payments.entity.CouponInfo;
import org.ml.mldj.rules.service.ICouponInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements ICouponInfoService {

}
