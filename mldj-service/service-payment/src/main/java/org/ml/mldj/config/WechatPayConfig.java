package org.ml.mldj.config;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import org.springframework.boot.context.properties.ConfigurationProperties;


import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 微信支付V3配置类
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wechat.pay.v3")
public class WechatPayConfig {

    /**
     * 微信小程序/公众号AppID
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户API V3密钥（32位）
     */
    private String apiV3Key;

    /**
     * 商户API证书序列号
     */
    private String serialNo;

    /**
     * 商户私钥文件路径
     */
    private String privateKeyPath;

    /**
     * 商户私钥内容（Base64编码）
     */
    private String privateKey;

    /**
     * 支付回调通知地址
     */
    private String notifyUrl;

    /**
     * 退款回调通知地址
     */
    private String refundNotifyUrl;

    /**
     * 微信支付证书保存路径
     */
    private String certSavePath = "/tmp/wechat_certs/";

    /**
     * 证书自动更新间隔（小时）
     */
    private int certUpdateInterval = 24;

    /**
     * 是否启用沙箱环境
     */
    private boolean sandbox = false;

    /**
     * 连接超时时间（毫秒）
     */
    private int connectTimeout = 5000;

    /**
     * 读取超时时间（毫秒）
     */
    private int readTimeout = 10000;

    /**
     * 最大重试次数
     */
    private int maxRetry = 3;


    @Bean
    public JsapiServiceExtension jsapiServiceExtension() {
        // 初始化商户配置
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(mchId)
                        // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
                        .privateKeyFromPath(privateKeyPath)
//                        .merchantSerialNumber(merchantSerialNumber)
                        .apiV3Key(apiV3Key)
                        .build();
        // 初始化服务
        return
                new JsapiServiceExtension.Builder()
                        .config(config)
                        .signType("RSA") // 不填默认为RSA
                        .build();
    }


    /**
     * 获取API域名
     */
    public String getApiDomain() {
        return sandbox ? "https://api.mch.weixin.qq.com/sandboxnew"
                : "https://api.mch.weixin.qq.com";
    }

    /**
     * 获取支付API URL
     */
    public String getPayApiUrl() {
        return getApiDomain() + "/v3/pay/transactions/jsapi";
    }

    /**
     * 获取退款API URL
     */
    public String getRefundApiUrl() {
        return getApiDomain() + "/v3/refund/domestic/refunds";
    }

    /**
     * 获取查询订单API URL
     */
    public String getQueryApiUrl() {
        return getApiDomain() + "/v3/pay/transactions/out-trade-no/";
    }

    /**
     * 获取证书下载API URL
     */
    public String getCertApiUrl() {
        return getApiDomain() + "/v3/certificates";
    }
}