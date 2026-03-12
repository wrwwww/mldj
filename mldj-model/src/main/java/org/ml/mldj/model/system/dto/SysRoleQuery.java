package org.ml.mldj.model.system.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.ml.mldj.model.common.QueryCondition;

@Setter
@Getter
public class SysRoleQuery {

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @QueryCondition(type = QueryCondition.Type.LIKE)
    private String name;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    @QueryCondition(type = QueryCondition.Type.LIKE)
    private String code;
}
