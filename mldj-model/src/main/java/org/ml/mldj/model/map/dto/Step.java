package org.ml.mldj.model.map.dto;

/**
 * 路线分段
 */
@lombok.Data
public class Step {
    /**
     * 设置后可返回方案所需时间及费用成本
     */
    private Cost cost;
    /**
     * 行驶指示
     */
    private String instruction;
    /**
     * 进入道路方向
     */
    private String orientation;
    /**
     * 分段道路名称
     */
    private String roadName;
    /**
     * 可选差异化结果返回
     */
    private String showFields;
    /**
     * 分段距离信息
     */
    private Double stepDistance;
}
