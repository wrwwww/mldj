package org.ml.mldj.model.vo.map;

/**
 * 设置后可返回方案所需时间及费用成本
 */
@lombok.Data
public class Cost {
    /**
     * 线路耗时，包括方案总耗时及分段step中的耗时
     */
    private String duration;
    /**
     * 预计出租车费用，单位：元
     */
    private String taxiFee;
    /**
     * 设置后可返回分段路况详情
     */
    private Tmcs tmcs;
    /**
     * 收费路段里程，单位：米，包括分段信息
     */
    private String tollDistance;
    /**
     * 主要收费道路
     */
    private String tollRoad;
    /**
     * 此路线道路收费，单位：元，包括分段信息
     */
    private String tolls;
    /**
     * 方案中红绿灯个数，单位：个
     */
    private Long trafficLights;
}
