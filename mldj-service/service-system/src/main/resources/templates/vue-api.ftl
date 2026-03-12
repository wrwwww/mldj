import type { SetOptional } from "type-fest";
import type { ${entityName} } from "./types";
import http from "@/utils/axios";

export interface ListSearchParams {

<#list searchColumns as column>
   <#if searchColumns.showInSearch>
       ${column.fieldName}: ${column.tsType};
   </#if>
</#list>
}



export class Api {
  /** ${title}分页 */
  static page(
    params: ApiUtil.WithPaginationParams<{<#if searchColumns?has_content> <#list searchColumns as column>${column.fieldName}?: ${column.tsType}<#if column_has_next>; </#if></#list> </#if>}>,
  ) {
    return http.get<ApiUtil.PaginationResponse<${entityName}>>(
      "/api/${moduleName}/${routePath}/page",
      { params },
    );
  }

  /** ${title}列表 */
  static list() {
    return http.get<${entityName}[]>("/api/${moduleName}/${routePath}/list");
  }

  /** ${title}详情 */
  static getById(id: string) {
    return http.get<${entityName}>(`/api/${moduleName}/${routePath}/\${id}`);
  }

  /** 新增或更新${title} */
  static insertOrUpdate(data: SetOptional<${entityName}, "id">) {
    return http({
      method: data.id ? "put" : "post",
      url: "/api/${moduleName}/${routePath}",
      data,
    });
  }

  /** 删除${title} */
  static del(id: string) {
    return http.delete(`/api/${moduleName}/${routePath}/\${id}`);
  }
}