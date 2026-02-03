-- ${title} 表结构
CREATE TABLE `${tableName}` (
<#list columns as column>
  `${column.columnName}` ${column.columnType}<#if column.dataLength??>(${column.dataLength})</#if><#if column.nullable == false> NOT NULL</#if><#if column.autoIncrement == true> AUTO_INCREMENT</#if><#if column.defaultValue??> DEFAULT <#if column.columnType?lower_case?contains("char") || column.columnType?lower_case?contains("text") || column.columnType?lower_case == "date" || column.columnType?lower_case == "datetime" || column.columnType?lower_case == "timestamp">'${column.defaultValue}'<#else>${column.defaultValue}</#if></#if><#if column.comment??> COMMENT '${column.comment}'</#if><#if column_has_next>,</#if>
</#list>
<#assign pkColumns = columns?filter(c -> c.primaryKey)>
<#if pkColumns?has_content>
  ,PRIMARY KEY (<#list pkColumns as pk>`${pk.columnName}`<#if pk_has_next>,</#if></#list>)
</#if>
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='${title}表';

<#if initData?? && initData?size gt 0>
  -- 初始化数据
  INSERT INTO `${tableName}` (
  <#list columns as column>
    `${column.columnName}`<#if column_has_next>,</#if>
  </#list>
  ) VALUES
  <#list initData as data>
    (<#list columns as column>
    <#assign value = data[column.columnName]!>
    <#if value??>
      <#if column.columnType?lower_case?contains("char") || column.columnType?lower_case?contains("text") || column.columnType?lower_case?contains("date") || column.columnType?lower_case?contains("time")>'${value?replace("'", "''")}'<#elseif value?is_string && (value == "NULL" || value == "null")>NULL<#else>${value}</#if>
    <#else>
      NULL
    </#if><#if column_has_next>,</#if>
  </#list>)<#if data_has_next>,</#if>
  </#list>;
</#if>