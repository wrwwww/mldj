package org.ml.mldj.model.vo.map;

import lombok.Data;

@Data
public class GaodeDrivingResultVO {
    /**
     * 路径规划方案总数
     */
    private Long count;
    /**
     *
     * 访问状态值的说明，如果成功返回"ok"，失败返回错误原因，具体见[错误码说明](https://lbs.amap.com/api/webservice/guide/tools/info)。
     */
    private String info;
    /**
     * 返回状态说明,10000代表正确,详情参阅info状态表
     */
    private Double infocode;
    /**
     * 返回的规划方案列表
     */
    private Route route;
    /**
     * 本次API访问状态，如果成功返回1，如果失败返回0。
     */
    private Double status;
}
