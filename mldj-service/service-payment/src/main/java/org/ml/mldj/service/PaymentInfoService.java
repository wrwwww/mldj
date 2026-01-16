package org.ml.mldj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.payments.entity.PaymentInfo;
import org.ml.mldj.mapper.PaymentInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class PaymentInfoService extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements IService<PaymentInfo> {

}
