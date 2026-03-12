package ${packageName}.entity;

import lombok.Data;
import java.time.LocalDateTime;
<#list columns as column>
<#if column.javaType == "BigDecimal">
import java.math.BigDecimal;
<#break>
</#if>
</#list>

/**
 * ${title} 实体类
 * 
 * @author CodeGenerator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Data
public class ${entityName}InsertForm {

<#list columns as column>

<#if column.showInInsert>
    /**
    * ${column.comment!column.label}
    */
    private ${column.javaType} ${column.label};
</#if>

</#list>
}