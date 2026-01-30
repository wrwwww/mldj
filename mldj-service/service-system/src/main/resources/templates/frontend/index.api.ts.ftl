<#assign entityName = config.entityName>
<#assign moduleName = config.moduleName>
<#assign routePath = config.routePath>
<#assign basePath = "/${moduleName}/${routePath}">

import type { SetOptional } from 'type-fest'
import { isNil } from 'lodash-es'
import http from '@/utils/axios'

export interface ${entityName} {
id: string
<#list config.columns as c>
    <#assign propName = c.columnName?replace("_([a-z])", "$1", "r")?uncap_first>
    <#assign required = c.required!false>
    ${propName}<#if !required>?</#if>: ${c.columnType?lower_case?switch(
"int" -> "number",
"integer" -> "number",
"bigint" -> "number",
"tinyint" -> "number",
"smallint" -> "number",
"decimal" -> "number",
"float" -> "number",
"double" -> "number",
"datetime" -> "string",
"date" -> "string",
"timestamp" -> "string",
"time" -> "string",
"bool" -> "boolean",
"boolean" -> "boolean",
"varchar" -> "string",
"char" -> "string",
"text" -> "string",
"longtext" -> "string",
"default" -> "string"
)};
</#list>
}

export interface ListSearchParams {
<#list config.columns as c>
    <#if c.showInSearch>
        <#assign propName = c.columnName?replace("_([a-z])", "$1", "r")?uncap_first>
        ${propName}?: string;
    </#if>
</#list>
}

export class Api {
static page(params: ApiUtil.WithPaginationParams<ListSearchParams>) {
    return http.get<ApiUtil.PaginationResponse<${entityName}>>('${basePath}/page', { params })
    }

    static get(id: string) {
    return http.get<${entityName}>(\`${basePath}/\${id}\`)
    }

    static insertOrUpdate(data: SetOptional<${entityName}, 'id'>) {
    return http({
    method: isNil(data.id) ? 'post' : 'put',
    url: '${basePath}',
    data,
    })
    }

    static del(id: string) {
    return http.delete(\`${basePath}/\${id}\`)
    }
    }