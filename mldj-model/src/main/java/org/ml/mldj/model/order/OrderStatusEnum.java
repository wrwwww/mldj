package org.ml.mldj.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    // 正常流程状态
    WAITING_DRIVER(10, "待接单", "等待司机接单"),
    ACCEPTED(20, "已接单", "司机已接单，赶赴中"),
    ARRIVED(30, "已到达", "司机已到达上车点"),
    ON_TRIP(40, "行程中", "行程已开始"),
    TRIP_ENDED(50, "行程结束", "到达目的地"),
    WAITING_PAYMENT(60, "待支付", "等待用户支付"),
    PAID(70, "已支付", "用户已支付"),
    COMPLETED(80, "已完成", "订单已完成"),

    // 取消状态
    USER_CANCELED(91, "用户取消", "用户主动取消"),
    DRIVER_CANCELED(92, "司机取消", "司机主动取消"),
    SYSTEM_CANCELED(93, "系统取消", "系统自动取消"),

    // 异常状态
    TRIP_EXCEPTION(95, "行程异常", "行程中出现异常"),
    DISPUTED(96, "争议中", "订单存在争议"),

    // 关闭状态
    CLOSED(99, "已关闭", "订单已关闭");

    private final int code;
    private final String name;
    private final String description;


    // 状态转换校验
//    public boolean canTransitionTo(OrderStatusEnum target) {
//        return StateTransitionRules.canTransition(this, target);
//    }

    // 业务方法
    public boolean canCancel() {
        return this == WAITING_DRIVER ||
                this == ACCEPTED ||
                this == ARRIVED;
    }

    public boolean canStartTrip() {
        return this == ARRIVED;
    }

    public boolean canEndTrip() {
        return this == ON_TRIP;
    }

    public boolean canPay() {
        return this == WAITING_PAYMENT;
    }

    public boolean isFinal() {
        return this == COMPLETED || this == CLOSED;
    }

    public boolean isActive() {
        return this.code >= 10 && this.code <= 70;
    }
}
