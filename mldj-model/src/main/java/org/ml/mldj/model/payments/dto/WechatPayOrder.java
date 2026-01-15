package org.ml.mldj.model.payments.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 微信支付订单实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WechatPayOrder {

    /**
     * 商户订单号（由商户生成，要求唯一）
     */
    private String orderId;

    /**
     * 微信支付订单号（由微信生成）
     */
    private String transactionId;

    /**
     * 预支付交易会话标识（prepay_id）
     */
    private String prepayId;

    /**
     * 小程序/公众号AppID
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 附加数据，在支付通知中原样返回
     */
    private String attach;

    /**
     * 支付金额信息
     */
    private Amount amount;

    /**
     * 支付者信息
     */
    private Payer payer;



    /**
     * 订单失效时间（格式：yyyy-MM-dd'T'HH:mm:ssXXX）
     */
    private String timeExpire;

    /**
     * 通知地址
     */
    private String notifyUrl;

    /**
     * 订单优惠标记
     */
    private String goodsTag;

    /**
     * 电子发票入口开放标识
     */
    private Boolean supportFapiao;

    /**
     * 支付订单状态
     */
    private int payStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 支付完成时间
     */
    private String payTime;

    /**
     * 业务订单ID（关联代驾订单）
     */
    private String businessOrderId;

    /**
     * 支付金额信息内部类
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Amount {
        /**
         * 总金额（单位：分）
         */
        private Integer total;

        /**
         * 货币类型（CNY：人民币）
         */
        @Builder.Default
        private String currency = "CNY";

        /**
         * 用户支付金额（单位：分）
         */
        private Integer payerTotal;

        /**
         * 货币类型（CNY：人民币）
         */
        private String payerCurrency;

        /**
         * 从 BigDecimal 创建 Amount
         */
        public static Amount fromBigDecimal(BigDecimal amount) {
            return Amount.builder()
                    .total(amount.multiply(new BigDecimal("100")).intValue())
                    .currency("CNY")
                    .build();
        }

        /**
         * 转换为 BigDecimal
         */
        public BigDecimal toBigDecimal() {
            return new BigDecimal(total).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_UP);
        }
    }

    /**
     * 支付者信息内部类
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Payer {
        /**
         * 用户标识（openid）
         */
        private String openid;

        /**
         * 用户姓名（需要实名认证）
         */
        private String name;

        /**
         * 用户证件号
         */
        private String idCardNumber;

        /**
         * 是否需要实名验证
         */
        @Builder.Default
        private Boolean needRealName = false;
    }




}