package org.ml.mldj.model.vo.map;

import java.util.List;

/**
 * 算路方案详情
 */
@lombok.Data
public class Path {
    /**
     * 方案距离，单位：米
     */
    private String distance;
    /**
     * false 代表限行已规避或未限行，即该路线没有限行路段；true 代表限行无法规避，即该线路有限行路段
     */
    private Boolean restriction;
    /**
     * 路线分段
     */
    private List<Step> steps;
}