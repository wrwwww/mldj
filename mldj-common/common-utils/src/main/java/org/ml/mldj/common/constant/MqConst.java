package org.ml.mldj.common.constant;

public class MqConst {
    // 交换机
    public static final String EXCHANGE_ORDER = "order.exchange";
    public static final String EXCHANGE_DIRECT = "direct.exchange";
    public static final String EXCHANGE_FANOUT = "fanout.exchange";
    public static final String EXCHANGE_TOPIC = "topic.exchange";

    // 队列
    public static final String QUEUE_PAY_SUCCESS = "order.pay.success.queue";
    public static final String QUEUE_ORDER_CREATED = "order.created.queue";
    public static final String QUEUE_ORDER_CANCELED = "order.canceled.queue";

    // 路由键
    public static final String ROUTING_PAY_SUCCESS = "order.pay.success";
    public static final String ROUTING_ORDER_CREATED = "order.created";
    public static final String ROUTING_ORDER_CANCELED = "order.canceled";

    // 死信队列相关
    public static final String DLX_EXCHANGE = "order.dlx.exchange";
    public static final String DLX_QUEUE = "order.dlx.queue";
    public static final String DLX_ROUTING_KEY = "order.dead";
}
