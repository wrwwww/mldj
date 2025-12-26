package org.ml.mldj.model.map.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NearbyDriver {
    @Schema(description = "司机id")
    String driverId;
    @Schema(description = "距离用户的距离")
    double distance;
    @Schema(description = "纬度")
    double latitude;
    @Schema(description = "经度")
    double longitude;
}
