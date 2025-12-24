package org.ml.mldj.model.dto.map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NearbyDriver {
    String driverId;
    double distance;
    double latitude;
    double longitude;
}
