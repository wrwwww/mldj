<#assign tableName = config.tableName>

-- ${config.title} 表结构（MySQL）
-- 若表已存在可忽略

CREATE TABLE IF NOT EXISTS `${tableName}` (
<#list config.columns as c>
  <#assign type = c.columnType?lower_case?switch(
    "varchar" -> "varchar(255)",
    "char" -> "char(50)",
    "text" -> "text",
    "longtext" -> "longtext",
    "int" -> "int",
    "integer" -> "int",
    "bigint" -> "bigint",
    "tinyint" -> "tinyint",
    "smallint" -> "smallint",
    "decimal" -> "decimal(19,2)",
    "float" -> "float",
    "double" -> "double",
    "datetime" -> "datetime",
    "date" -> "date",
    "timestamp" -> "timestamp",
    "time" -> "time",
    "bool" -> "tinyint(1)",
    "boolean" -> "tinyint(1)",
    "default" -> "varchar(255)"
  )>
  <#assign required = c.required!false>
  <#assign comment = c.comment!c.label>
  `${c.columnName}` ${type} <#if required>NOT NULL</#if><#if c.columnName == "id"> PRIMARY KEY</#if> COMMENT '${comment}'<#if c_has_next>,</#if>
</#list>,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='${config.title}';