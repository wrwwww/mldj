package org.ml.mldj.model.dto.map;

import lombok.Builder;
import lombok.Data;

// 过滤器
@Data
@Builder
public class DriverFilter {
    private DriverLocation.DriverStatus status;
    private Double minRating;
    private String vehicleType;
    private boolean excludeBusy;
    private Integer maxDistance; // 最大距离（米）
}