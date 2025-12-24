package org.ml.mldj.customer.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ml.mldj.map.client.MapFeignClient;
import org.ml.mldj.model.dto.*;
import org.ml.mldj.model.dto.customer.CreateNewOrderForm;
import org.ml.mldj.model.dto.customer.OrderForm;
import org.ml.mldj.model.dto.customer.SendNewOrderMessageForm;
import org.ml.mldj.model.vo.*;
import org.ml.mldj.model.vo.customer.OrderVO;
import org.ml.mldj.order.client.OrderFeignClient;
import org.ml.mldj.rules.client.RulesFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class OrderService {

    @Autowired
    OrderFeignClient orderFeignClient;
    @Autowired
    RulesFeignClient rulesFeignClient;
    @Autowired
    MapFeignClient mapFeignClient;

    public HashMap createNewOrder(@Valid CreateNewOrderForm form) {

        // 估算里程和时间
        OrderMileageAndMinuteForm orderMileageAndMinuteForm = new OrderMileageAndMinuteForm();
        BeanUtils.copyProperties(form, orderMileageAndMinuteForm);
        orderMileageAndMinuteForm.setMode("driving");
        OrderMileageAndMinuteVO mileageAndMinuteVO = mapFeignClient.estimateOrderMileageAndMinute(orderMileageAndMinuteForm).unwrap();
        // 利用里程和时间计算费用

        CalOrderFeeForm calOrderFeeForm = new CalOrderFeeForm();
        calOrderFeeForm.setMileage(mileageAndMinuteVO.getMileage());
        calOrderFeeForm.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:ss")));
        CalOrderFeeVO calOrderFeeVO = rulesFeignClient.calculateOrderFee(calOrderFeeForm).unwrap();
        // 查找可接单的司机
        BefittingDriversForm befittingDriversForm = new BefittingDriversForm();
        BeanUtils.copyProperties(orderMileageAndMinuteForm, befittingDriversForm);
        befittingDriversForm.setMileage(mileageAndMinuteVO.getMileage());

        BefittingDriversVO befittingDriversVO = mapFeignClient.searchBefittingDrivers(befittingDriversForm).unwrap();
        // 生成订单
        OrderForm orderForm = new OrderForm();

        OrderVO orderVO = orderFeignClient.createOrder(orderForm).unwrap();
        // 发送订单给可接单的司机
        SendNewOrderMessageForm sendNewOrderMessageForm = new SendNewOrderMessageForm();

        return null;
    }

    public OrderVO query(String orderId, String customerId) {

        OrderVO vo = orderFeignClient.query(orderId, customerId).unwrap();
        return vo;
    }

    public int delOrder(String orderId, String customerId) {
        int unwrap = orderFeignClient.del(orderId, customerId).unwrap();
        return 0;
    }

    public OrderVO hasCustomerCurrentOrder(String customerId) {
        return null;
    }

    public PageVO<OrderVO> page(PageForm form, String customerId) {
        PageVO<OrderVO> vo = orderFeignClient.page(form, customerId).unwrap();
        return vo;
    }
}
