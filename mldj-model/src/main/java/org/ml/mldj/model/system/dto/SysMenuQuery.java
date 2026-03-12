package org.ml.mldj.model.system.dto;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单表 查询
 *
 * @author CodeGenerator
 * @date 2026-03-12 16:07:24
 */
@Data
public class SysMenuQuery {

    /**
     * name
     *
     * 数据库字段：name
     * 字段类型：VARCHAR
     */
    @Schema(
            description = "name",
            example = "请输入name"
    )
    private String name;



    /**
     * type
     *
     * 数据库字段：type
     * 字段类型：INT
     */
    @Schema(
            description = "type",
            example = "请选择type"
    )
    private String type;



    /**
     * path
     *
     * 数据库字段：path
     * 字段类型：VARCHAR
     */
    @Schema(
            description = "path",
            example = "请输入path"
    )
    private String path;



    /**
     * status
     *
     * 数据库字段：status
     * 字段类型：INT
     */
    @Schema(
            description = "status"
    )
    private String status;



    /**
     * active_menu
     *
     * 数据库字段：active_menu
     * 字段类型：VARCHAR
     */
    @Schema(
            description = "active_menu",
            example = "请输入active_menu"
    )
    private String active_menu;


}