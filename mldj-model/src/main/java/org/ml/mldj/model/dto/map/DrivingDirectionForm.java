package org.ml.mldj.model.dto.map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DrivingDirectionForm {
    String key;
    String origin;
    String destination;
    String originid;
    String destinationid;
    String destinationtype;
    String strategy;
    // 经度和纬度用,分割，经度在前，纬度在后，小数点后不超过6位，坐标点之间用;分隔最大数目：16个坐标点。
    // 如果输入多个途径点，则按照用户输入的顺序进行路径规划
    @Schema(description = " 途经点")
    String waypoints;
}
