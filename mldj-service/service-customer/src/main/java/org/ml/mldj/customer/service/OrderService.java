package org.ml.mldj.customer.service;

import jakarta.validation.Valid;
import org.ml.mldj.common.constant.MqConst;
import org.ml.mldj.driver.client.DriverLocationFeignClient;
import org.ml.mldj.map.client.MapFeignClient;
import org.ml.mldj.model.customer.dto.BefittingDriversForm;
import org.ml.mldj.model.customer.dto.CalOrderFeeForm;
import org.ml.mldj.model.order.dto.OrderMileageAndMinuteForm;
import org.ml.mldj.model.common.PageForm;
import org.ml.mldj.model.customer.dto.CreateNewOrderForm;
import org.ml.mldj.model.customer.dto.OrderForm;
import org.ml.mldj.model.customer.dto.SendNewOrderMessageForm;
import org.ml.mldj.model.map.dto.DriverFilter;
import org.ml.mldj.model.map.dto.DriverLocation;
import org.ml.mldj.model.map.dto.NearbyDriver;
import org.ml.mldj.model.order.vo.CalOrderFeeVO;
import org.ml.mldj.model.order.vo.OrderMileageAndMinuteVO;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.order.vo.OrderVO;
import org.ml.mldj.order.client.OrderFeignClient;
import org.ml.mldj.rules.client.RulesFeignClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderFeignClient orderFeignClient;
    @Autowired
    RulesFeignClient rulesFeignClient;
    @Autowired
    MapFeignClient mapFeignClient;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    DriverLocationFeignClient driverLOcationFeignClient;

    public HashMap createNewOrder(@Valid CreateNewOrderForm form) {

        // 估算里程和时间
        OrderMileageAndMinuteForm orderMileageAndMinuteForm = new OrderMileageAndMinuteForm();
        BeanUtils.copyProperties(form, orderMileageAndMinuteForm);
        orderMileageAndMinuteForm.setMode("driving");
        OrderMileageAndMinuteVO mileageAndMinuteVO = mapFeignClient.estimateOrderMileageAndMinute(orderMileageAndMinuteForm).unwrap();
        // 利用里程和时间计算费用

        CalOrderFeeForm calOrderFeeForm = new CalOrderFeeForm();
        calOrderFeeForm.setMileage(mileageAndMinuteVO.getDuration());
        calOrderFeeForm.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:ss")));
        CalOrderFeeVO calOrderFeeVO = rulesFeignClient.calculateOrderFee(calOrderFeeForm).unwrap();
        // 查找可接单的司机
        BefittingDriversForm befittingDriversForm = new BefittingDriversForm();
        BeanUtils.copyProperties(orderMileageAndMinuteForm, befittingDriversForm);
        befittingDriversForm.setMileage(mileageAndMinuteVO.getDistance());
        List<NearbyDriver> unwrap = driverLOcationFeignClient.findNearbyDriversWithFilter(befittingDriversForm).unwrap();


        // 生成订单
        OrderForm orderForm = new OrderForm();

        OrderVO orderVO = orderFeignClient.createOrder(orderForm).unwrap();
        // 发送订单给可接单的司机
        SendNewOrderMessageForm sendNewOrderMessageForm = new SendNewOrderMessageForm();
        List<String> list = unwrap.stream().map(nearbyDriver -> String.format("%s#%s", nearbyDriver.getDriverId(), nearbyDriver.getDistance())).toList();
        sendNewOrderMessageForm.setDriversContent(list);
        sendNewOrderMessageForm.setOrderId(orderVO.getOrderId());
        sendNewOrderMessageForm.setMileage(mileageAndMinuteVO.getDuration());
        sendCreateOrder(sendNewOrderMessageForm);

        return null;
    }

    private void sendCreateOrder(SendNewOrderMessageForm form) {
        rabbitTemplate.convertAndSend(MqConst.EXCHANGE_ORDER, MqConst.QUEUE_ORDER_CREATED, form);
    }


    private boolean filterDriver(DriverLocation driver, DriverFilter filter) {
        // 状态过滤
        if (filter.getStatus() != null && driver.getStatus() != filter.getStatus()) {
            return false;
        }

        // 评分过滤
        if (filter.getMinRating() != null && driver.getRating() < filter.getMinRating()) {
            return false;
        }

        // 车辆类型过滤
        if (filter.getVehicleType() != null &&
                !filter.getVehicleType().equals(driver.getVehicle().getModel())) {
            return false;
        }

        // 是否忙碌
        if (filter.isExcludeBusy() && driver.getCurrentOrderId() != null) {
            return false;
        }

        return true;
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
