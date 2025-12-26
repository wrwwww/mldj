package org.ml.mldj.order.service;

import org.ml.mldj.model.order.entity.OrderTrack;
import org.ml.mldj.order.mapper.OrderTrackMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单轨迹记录表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class OrderTrackServiceImpl extends ServiceImpl<OrderTrackMapper, OrderTrack> implements IOrderTrackService {

}
