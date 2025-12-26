package org.ml.mldj.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.order.entity.OrderStatusLog;
import org.ml.mldj.order.mapper.OrderStatusLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单状态变更日志表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class OrderStatusLogServiceImpl extends ServiceImpl<OrderStatusLogMapper, OrderStatusLog> implements IService<OrderStatusLog> {

}
