package org.ml.mldj.model.vo.map;

import lombok.Data;

import java.util.List;

@Data
public class Route {
    /**
     * 终点经纬度
     */
    private String destination;
    /**
     * 起点经纬度
     */
    private String origin;
    /**
     * 算路方案详情
     */
    private List<Path> paths;
}
