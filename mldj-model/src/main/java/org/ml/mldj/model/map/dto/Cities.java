package org.ml.mldj.model.map.dto;

/**
 * 设置后可返回分段途径城市信息
 */
@lombok.Data
public class Cities {
    /**
     * 途径区域编码
     */
    private Long adcode;
    /**
     * 途径城市名称
     */
    private String city;
    /**
     * 途径城市编码
     */
    private Long citycode;
    /**
     * 途径区县信息
     */
    private District district;
}