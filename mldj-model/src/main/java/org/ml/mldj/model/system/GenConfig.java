package org.ml.mldj.model.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 代码生成配置
 */
@Data
@TableName(value = "gen_config", autoResultMap = true)
public class GenConfig {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /** 数据源ID */
    private String datasourceId;

    /** 表名 */
    private String tableName;

    /** 模块名，如 system */
    private String moduleName;

    /** 实体名（大驼峰），如 User */
    private String entityName;

    /** 功能标题，如 用户管理 */
    private String title;

    /** 路由路径，如 user */
    private String routePath;

    /** 列配置（JSON存储） */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ColumnConfig> columns;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 列配置
     */
    @Data
    public static class ColumnConfig {
        /** 列名 */
        private String columnName;

        /** 列类型 */
        private String columnType;

        /** 注释 */
        private String comment;

        /** 列表展示 */
        private Boolean showInList;

        /** 搜索条件 */
        private Boolean showInSearch;

        /** 表单编辑 */
        private Boolean showInForm;

        /** 展示标签（默认用 comment） */
        private String label;

        /** 表单控件类型: input/select/date/textarea 等 */
        private String formType;

        /** 列表宽度 */
        private Integer listWidth;

        /** 是否必填 */
        private Boolean required;
    }
}