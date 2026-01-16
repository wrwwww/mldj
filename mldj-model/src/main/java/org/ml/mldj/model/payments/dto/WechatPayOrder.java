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
    private BigDecimal amount;

    /**
     * 支付者信息
     */
    private String payerOpenId;





}