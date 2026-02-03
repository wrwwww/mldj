package ${packageName}.controller;

import ${packageName}.entity.${entityName};
import ${packageName}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ${title} Controller
 * 
 * @author CodeGenerator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@RestController
@RequestMapping("/${routePath}")
public class ${entityName}Controller {

    @Autowired
    private ${entityName}Service ${entityName?uncap_first}Service;

    /**
     * 分页查询${title}
     */
    @GetMapping("/page")
    public ApiResult<PageResult<${entityName}>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize<#if searchColumns?has_content>,
<#list searchColumns as column>
            @RequestParam(required = false) ${column.javaType} ${column.fieldName}<#if column_has_next>,</#if>
</#list></#if>) {
        
        PageResult<${entityName}> result = ${entityName?uncap_first}Service.page(page, pageSize<#if searchColumns?has_content>, <#list searchColumns as column>${column.fieldName}<#if column_has_next>, </#if></#list></#if>);
        return ApiResult.success(result);
    }

    /**
     * 根据ID查询${title}
     */
    @GetMapping("/{id}")
    public ApiResult<${entityName}> getById(@PathVariable String id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.getById(id);
        return ApiResult.success(${entityName?uncap_first});
    }

    /**
     * 新增${title}
     */
    @PostMapping
    public ApiResult<String> save(@RequestBody ${entityName} ${entityName?uncap_first}) {
        String id = ${entityName?uncap_first}Service.save(${entityName?uncap_first});
        return ApiResult.success(id);
    }

    /**
     * 修改${title}
     */
    @PutMapping
    public ApiResult<Void> update(@RequestBody ${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.update(${entityName?uncap_first});
        return ApiResult.success();
    }

    /**
     * 删除${title}
     */
    @DeleteMapping("/{id}")
    public ApiResult<Void> delete(@PathVariable String id) {
        ${entityName?uncap_first}Service.delete(id);
        return ApiResult.success();
    }
}