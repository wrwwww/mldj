package org.ml.mldj.model.system.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户分配角色请求对象
 */
@Data
public class AssginRoleVo {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 选中的角色ID列表
     */
    private List<Long> roleIdList;
}
