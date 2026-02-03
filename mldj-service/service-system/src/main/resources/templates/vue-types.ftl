/** ${title} */
export interface ${entityName} {
<#list columns as column>
  /** ${column.comment!column.fieldName} */
  ${column.fieldName}: ${column.tsType};
</#list>
}