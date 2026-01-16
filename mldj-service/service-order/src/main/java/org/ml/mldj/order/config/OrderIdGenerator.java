package org.ml.mldj.order.config;

import java.util.List;

public interface OrderIdGenerator {

    /**
     * 生成订单ID
     * @param cityCode 城市代码
     * @param userId 用户ID（可选，用于生成用户相关ID）
     * @return 订单ID
     */
    String generate(String cityCode, Long userId);

    /**
     * 解析订单ID
     * @param orderId 订单ID
     * @return 订单ID信息
     */
//    OrderIdInfo parse(String orderId);

    /**
     * 批量生成订单ID
     * @param cityCode 城市代码
     * @param count 数量
     * @return 订单ID列表
     */
    List<String> batchGenerate(String cityCode, int count);
}