package org.ml.mldj.model.map.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EstimateRouteForm {
    @Data
    public static
    class Route{
       int id;
       BigDecimal latitude;
       BigDecimal longitude;
    }
    List<Route> routes;
}
