package ${packageName}.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("${tableName}")
public class ${entityName} {

<#list columns as column>
    /**
     * ${column.comment!column.fieldName}
     */
<#if column.primaryKey>
    @TableId
</#if>
    private ${column.javaType} ${column.fieldName};

</#list>
}