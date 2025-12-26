package org.ml.mldj.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ml.mldj.model.order.entity.OrderJob;
import org.ml.mldj.order.mapper.OrderJobMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单任务关联表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class OrderJobServiceImpl extends ServiceImpl<OrderJobMapper, OrderJob> implements IService<OrderJob> {

}
