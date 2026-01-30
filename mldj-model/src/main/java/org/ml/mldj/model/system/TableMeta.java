package org.ml.mldj.model.system;


import lombok.Data;

import java.util.List;

/**
 * 表元数据信息
 */
@Data
public class TableMeta {
    /** 表名 */
    private String tableName;

    /** 表注释 */
    private String tableComment;

    /** 列信息 */
    private List<ColumnMeta> columns;

    @Data
    public static class ColumnMeta {
        /** 列名 */
        private String columnName;

        /** 列类型 */
        private String columnType;

        /** 列注释 */
        private String columnComment;

        /** 是否主键 */
        private Boolean isPrimaryKey;

        /** 是否可为空 */
        private Boolean isNullable;

        /** 字段长度 */
        private Integer columnSize;

        /** 数字精度 */
        private Integer decimalDigits;
    }
}