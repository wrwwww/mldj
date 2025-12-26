package org.ml.mldj.model.map.dto;

/**
 * 途径区县信息
 */
@lombok.Data
public class District {
    /**
     * 途径区县adcode
     */
    private Long adcode;
    /**
     * 途径区县名称
     */
    private String name;
    /**
     * 设置后可返回分路段坐标点串，两点间用“,”分隔
     */
    private String polyline;
}