package org.ml.mldj.model.map.dto;

import lombok.Data;

@Data
public class DistanceVO {
    String dest_id;
    String origin_id;
    String distance;
    String duration;
}
