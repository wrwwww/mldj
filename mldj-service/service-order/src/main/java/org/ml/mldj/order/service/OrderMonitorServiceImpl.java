package org.ml.mldj.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.order.entity.OrderMonitor;
import org.ml.mldj.order.mapper.OrderMonitorMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单监控表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class OrderMonitorServiceImpl extends ServiceImpl<OrderMonitorMapper, OrderMonitor> implements IService<OrderMonitor> {

}
