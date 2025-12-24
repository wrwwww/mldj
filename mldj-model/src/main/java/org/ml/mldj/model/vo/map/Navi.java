package org.ml.mldj.model.vo.map;

/**
 * 设置后可返回详细导航动作指令
 */
@lombok.Data
public class Navi {
    /**
     * 导航主要动作指令
     */
    private String action;
    /**
     * 导航辅助动作指令
     */
    private String assistantAction;
    /**
     * 设置后可返回分段途径城市信息
     */
    private Cities cities;
}
