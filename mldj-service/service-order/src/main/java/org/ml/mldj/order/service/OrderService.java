package org.ml.mldj.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.constant.RedisConst;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.ResultCode;
import org.ml.mldj.model.dto.customer.OrderForm;
import org.ml.mldj.model.order.entity.Order;
import org.ml.mldj.model.order.form.OrderPageForm;
import org.ml.mldj.model.vo.PageVO;
import org.ml.mldj.model.order.vo.OrderVO;
import org.ml.mldj.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    OrderMapper orderMapper;

    @Transactional
    public Result<?> snatchingOrder(String driverId, String orderId) {
        String key = RedisConst.ORDER_KEY_PREFIX + orderId;
        // 获取订单状态
        if (!redisTemplate.hasKey(key)) {
            throw new BizException(ResultCode.ORDER_EXIST);
        }
        String s = redisTemplate.opsForValue().get(key);
        // 获取锁
        this.redisTemplate.execute(new SessionCallback<>() {

            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch(key);
                operations.multi();
                operations.opsForValue().set(key, driverId);
                return operations.exec();
            }
        });
        redisTemplate.delete(key);
        // 订单入库
        int rows = orderMapper.updateOrderDriver(orderId, driverId);
        if (rows != 1) {
            log.error("订单:{},司机:{}入库失败", orderId, driverId);
            throw new BizException(ResultCode.ORDER_EXIST);
        }
        return Result.success(null, "抢单成功");
    }

    public Order query(String orderId) {
        // todo 从redis中拿订单详情

        // 从数据库中获取订单详情
        Order order = orderMapper.queryOrderById(orderId);

        return order;
    }

    public PageVO<OrderVO> page(OrderPageForm pageForm) {
        Page<OrderVO> page = orderMapper.page(pageForm);
        return PageVO.buildPageVO(page);
    }

    public Object add(OrderForm form) {

        return  null;
    }
}
