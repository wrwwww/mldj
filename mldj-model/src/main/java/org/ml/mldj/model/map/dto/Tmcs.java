package org.ml.mldj.model.map.dto;

/**
 * 设置后可返回分段路况详情
 */
@lombok.Data
public class Tmcs {
    /**
     * 设置后可返回详细导航动作指令
     */
    private Navi navi;
    /**
     * 从当前坐标点开始step中路况相同的距离
     */
    private Double tmcDistance;
    /**
     * 此段路况涉及的道路坐标点串，点间用","分隔
     */
    private String tmcPolyline;
    /**
     * 路况信息，包括：未知、畅通、缓行、拥堵、严重拥堵
     */
    private String tmcStatus;
}