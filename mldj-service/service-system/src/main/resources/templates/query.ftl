package ${packageName}.dao;


import lombok.Data;
import java.time.LocalDateTime;
<#list columns as column>
<#if column.javaType == "BigDecimal">
import java.math.BigDecimal;
<#break>
</#if>
</#list>

/**
 * ${title} 查询
 * 
 * @author CodeGenerator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Data
public class ${entityName}Query {
<#-- 过滤出需要查询的字段，并生成属性 -->
<#list columns as column>
    <#if column.showInSearch>

    /**
     * ${column.comment!column.label}
     * <#if column.required>必填</#if>
     * 数据库字段：${column.columnName}
     * 字段类型：${column.columnType}
     */
    @Schema(
        description = "${column.comment!column.label}",
        <#if column.formType == 'input'>example = "请输入${column.label}"<#elseif column.formType == 'select'>example = "请选择${column.label}"<#elseif column.formType == 'date'>example = "2024-01-01"<#elseif column.formType == 'datetime'>example = "2024-01-01 00:00:00"</#if>
    )
    <#-- 字段类型映射 -->
    private String ${column.label?uncap_first};


    </#if>
</#list>
}