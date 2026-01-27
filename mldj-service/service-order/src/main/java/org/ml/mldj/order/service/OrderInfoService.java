package org.ml.mldj.order.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.constant.RedisConst;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.common.utils.ResultCode;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.customer.dto.OrderForm;
import org.ml.mldj.model.order.OrderStatusEnum;
import org.ml.mldj.model.order.dto.OrderPageForm;
import org.ml.mldj.model.order.entity.OrderInfo;
import org.ml.mldj.model.order.vo.OrderVO;
import org.ml.mldj.order.config.SnowflakeOrderIdGenerator;
import org.ml.mldj.order.mapper.OrderInfoMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */

@Slf4j
@Service
public class OrderInfoService {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    RedissonClient redissonClient;

    @Transactional
    public Result<?> snatchingOrder(String driverId, String orderId) {
        String key = RedisConst.ORDER_KEY_PREFIX + orderId;
        // 获取订单状态
        if (!redisTemplate.hasKey(key)) {
            throw new BizException(ResultCode.ORDER_EXIST);
        }
        RLock lock = redissonClient.getLock(RedisConst.ORDER_KEY_LOCK + orderId);
        try {
            boolean flag = lock.tryLock(1, 1, TimeUnit.DAYS);
            if (flag) {

                if (!redisTemplate.hasKey(key)) {
                    throw new BizException(ResultCode.ORDER_EXIST);
                }
                // 订单入库
                LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.eq(OrderInfo::getOrderNo, orderId).isNull(OrderInfo::getDriverId).eq(OrderInfo::getStatus, OrderStatusEnum.WAITING_DRIVER);
                updateWrapper.set(OrderInfo::getDriverId, driverId)
                        .set(OrderInfo::getStatus, OrderStatusEnum.ACCEPTED)
                        .set(OrderInfo::getStartServiceTime, System.currentTimeMillis())
                ;
                int rows = orderInfoMapper.update(updateWrapper);
                if (rows != 1) {
                    log.error("订单:{},司机:{}入库失败", orderId, driverId);
                    throw new BizException(ResultCode.ORDER_EXIST);
                }

            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return Result.success(null, "抢单成功");
    }

    public OrderInfo query(String orderId) {
        // todo 从redis中拿订单详情

        // 从数据库中获取订单详情
        OrderInfo order = orderInfoMapper.queryOrderById(orderId);

        return order;
    }

    public PageVO<OrderVO> page(OrderPageForm pageForm) {
        Page<OrderVO> page = orderInfoMapper.page(pageForm);
        return PageVO.buildPageVO(page);
    }

    @Autowired
    SnowflakeOrderIdGenerator snowflakeOrderIdGenerator;

    public OrderVO add(OrderForm form) {

        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(form, orderInfo);
        orderInfo.setStatus(OrderStatusEnum.WAITING_DRIVER.getCode());
        orderInfo.setOrderNo(snowflakeOrderIdGenerator.generate("", form.getCustomerId()));
        int rows = orderInfoMapper.insert(orderInfo);
        if (rows != 1) {
            log.error("订单创建失败");
            throw new BizException(ResultCode.ORDER_CREATE_ERROR);
        }
        log.info("订单：{}，创建成功", orderInfo.getOrderNo());
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderInfo, orderVO);

        return orderVO;
    }

    public Object paymentSuccess(String orderNo) {
        LambdaUpdateWrapper<OrderInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(OrderInfo::getOrderNo, orderNo).eq(OrderInfo::getStatus, 1);
        int update = orderInfoMapper.update(updateWrapper);
        if (update < 1) {
            log.error("订单:{},状态更新失败", orderNo);
            throw new BizException("");
        }


        return true;
    }

    public PageVO<OrderVO> page(PageQuery<OrderInfo> query) {
        Page<OrderInfo> page = new Page<>(
                query.getPageSize(),
                query.getPageSize()
        );
        LambdaQueryWrapper<OrderInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderInfo::getOrderNo, query.getFilters().getOrderNo())
                .eq(OrderInfo::getStatus, query.getFilters().getStatus())
                .eq(OrderInfo::getDriverId, query.getFilters().getDriverId())
                .eq(OrderInfo::getCustomerId, query.getFilters().getCustomerId())

        ;

        Page<OrderInfo> orderInfoPage = orderInfoMapper.selectPage(page, queryWrapper);
        PageVO<OrderInfo> orderList = PageVO.buildPageVO(orderInfoPage);
        List<OrderVO> result = orderList.getList().stream().map(orderInfo -> {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(orderInfo, orderVO);
            return orderVO;
        }).toList();
        PageVO<OrderVO> orderVOPageVO = new PageVO<>();
        BeanUtils.copyProperties(orderList, orderVOPageVO);
        orderVOPageVO.setList(result);
        return orderVOPageVO;
    }
}
