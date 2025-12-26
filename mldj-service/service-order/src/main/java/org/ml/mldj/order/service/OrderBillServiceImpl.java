package org.ml.mldj.order.service;

import org.ml.mldj.model.order.entity.OrderBill;
import org.ml.mldj.order.mapper.OrderBillMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单账单明细表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@Service
public class OrderBillServiceImpl extends ServiceImpl<OrderBillMapper, OrderBill> implements IOrderBillService {

}
