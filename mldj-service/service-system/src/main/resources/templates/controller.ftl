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
     * 分页查询
     */
    @GetMapping("/page")
    public Result<PageResult<${entityName}>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize<#if searchColumns?has_content>,
<#list searchColumns as column>
            @RequestParam(required = false) ${column.javaType} ${column.fieldName}<#if column_has_next>,</#if>
</#list></#if>) {
        
        PageResult<${entityName}> result = ${entityName?uncap_first}Service.page(page, pageSize<#if searchColumns?has_content>, <#list searchColumns as column>${column.fieldName}<#if column_has_next>, </#if></#list></#if>);
        return Result.success(result);
    }

        /**
        * 分页查询${title}
        */
        @PostMapping("/page")
        public Result<PageVO<${entityName}>> page(@RequestBody PageQuery<${entityName}Query> pageQuery) {
            return Result.success(${entityName?uncap_first}Service.page(pageQuery));
        }

        /**
     * ${title}列表
     */
    @GetMapping("/list")
    public Result<List<${entityName}VO>> list() {
        List<${entityName}> list = ${entityName?uncap_first}Service.list();
        return Result.success(list);
    }

    /**
     * 根据ID查询${title}
     */
    @GetMapping("/{id}")
    public Result<${entityName}VO> getById(@PathVariable String id) {
        ${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.getById(id);
        return Result.success(${entityName?uncap_first});
    }

    /**
     * 新增${title}
     */
    @PostMapping
    public Result<?> save(@RequestBody ${entityName}InsertForm ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.save(${entityName?uncap_first});
        return Result.success();
    }

    /**
     * 修改${title}
     */
    @PutMapping
    public Result<?> update(@RequestBody ${entityName}UpdateForm ${entityName?uncap_first}) {
        ${entityName?uncap_first}Service.update(${entityName?uncap_first});
        return Result.success();
    }

    /**
     * 删除${title}
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable String id) {
        ${entityName?uncap_first}Service.delete(id);
        return Result.success();
    }
}