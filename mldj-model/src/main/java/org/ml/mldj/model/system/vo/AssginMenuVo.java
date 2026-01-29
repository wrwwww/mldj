package org.ml.mldj.model.system.vo;

import lombok.Data;

import java.util.List;

/**
 * 角色分配菜单请求对象
 */
@Data
public class AssginMenuVo {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 选中的菜单ID列表
     */
    private List<Long> menuIdList;
}
