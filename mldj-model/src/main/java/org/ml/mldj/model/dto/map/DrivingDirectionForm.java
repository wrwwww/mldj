package org.ml.mldj.model.dto.map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DrivingDirectionForm {
    String key;
    /**
     * 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。
     */
    String origin;
    /**
     * 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。
     */
    String destination;
    /**
     * 起点为 POI 时，建议填充此值，可提升路线规划准确性
     */
    String originid;
    String destinationid;
    /**
     * 当用户知道终点 POI 的类别时候，建议填充此值
     */
    String destinationtype;
    String strategy;
    // 经度和纬度用,分割，经度在前，纬度在后，小数点后不超过6位，坐标点之间用;分隔最大数目：16个坐标点。
    // 如果输入多个途径点，则按照用户输入的顺序进行路径规划
    @Schema(description = " 途经点")
    String waypoints;
}
