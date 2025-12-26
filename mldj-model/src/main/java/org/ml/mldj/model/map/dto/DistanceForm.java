package org.ml.mldj.model.map.dto;

import lombok.Data;

@Data
public class DistanceForm {

    String key;
    /**
     * 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。
     */
    String origin;
    /**
     * 经度在前，纬度在后，经度和纬度用","分割，经纬度小数点后不得超过6位。
     */
    String destination;
    String type;
}
