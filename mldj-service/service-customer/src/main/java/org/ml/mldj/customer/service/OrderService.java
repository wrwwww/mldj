package org.ml.mldj.customer.service;

import jakarta.validation.Valid;
import org.ml.mldj.map.client.MapFeignClient;
import org.ml.mldj.model.dto.BefittingDriversForm;
import org.ml.mldj.model.dto.OrderChargeForm;
import org.ml.mldj.model.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.dto.customer.CreateNewOrderForm;
import org.ml.mldj.model.dto.customer.OrderForm;
import org.ml.mldj.model.dto.customer.SendNewOrderMessageForm;
import org.ml.mldj.model.vo.BefittingDriversVO;
import org.ml.mldj.model.vo.OrderChargeVO;
import org.ml.mldj.model.vo.OrderMileageAndMinuteVO;
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

    //    @Autowired
    public HashMap createNewOrder(@Valid CreateNewOrderForm form) {

        // 估算里程和时间
        OrderMileageAndMinuteForm orderMileageAndMinuteForm = new OrderMileageAndMinuteForm();
        BeanUtils.copyProperties(form, orderMileageAndMinuteForm);
        orderMileageAndMinuteForm.setMode("driving");
        OrderMileageAndMinuteVO mileageAndMinuteVO = mapFeignClient.estimateOrderMileageAndMinute(orderMileageAndMinuteForm).unwrap();
        // 利用里程和时间计算费用

        OrderChargeForm orderChargeForm = new OrderChargeForm();
        orderChargeForm.setMileage(mileageAndMinuteVO.getMileage());
        orderChargeForm.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:ss")));
        OrderChargeVO chargeVOResult = rulesFeignClient.estimateOrderCharge(orderChargeForm).unwrap();
        // 查找可接单的司机
        BefittingDriversForm befittingDriversForm = new BefittingDriversForm();
        BeanUtils.copyProperties(orderMileageAndMinuteForm, befittingDriversForm);
        befittingDriversForm.setMileage(mileageAndMinuteVO.getMileage());

        BefittingDriversVO befittingDriversVO = mapFeignClient.searchBefittingDrivers(befittingDriversForm).unwrap();
        // 生成订单
        OrderForm orderForm = new OrderForm();

        OrderVO orderVO=orderFeignClient.createOrder(orderForm).unwrap();
        // 发送订单给可接单的司机
        SendNewOrderMessageForm sendNewOrderMessageForm = new SendNewOrderMessageForm();
        
        return null;
    }
}
