package org.ml.mldj.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.order.entity.OrderProfitSharing;
import org.ml.mldj.order.mapper.OrderProfitSharingMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单分润明细表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class OrderProfitSharingServiceImpl extends ServiceImpl<OrderProfitSharingMapper, OrderProfitSharing> implements IService<OrderProfitSharing> {

}
