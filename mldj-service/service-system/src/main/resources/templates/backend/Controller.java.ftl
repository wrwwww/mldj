<#assign entityName = config.entityName>
<#assign moduleName = config.moduleName>
<#assign routePath = config.routePath>
<#assign basePath = "/${moduleName}/${routePath}">
<#assign varName = entityName?uncap_first>

package com.example.${moduleName}.controller;

import com.example.${moduleName}.entity.${entityName};
import com.example.${moduleName}.service.${entityName}Service;
import com.example.common.Result;
import com.example.common.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
* ${config.title} - Controller
*/
@RestController
@RequestMapping("${basePath}")
@RequiredArgsConstructor
public class ${entityName}Controller {

private final ${entityName}Service ${varName}Service;

@GetMapping("/page")
public Result<PageResult<${entityName}>> page(
@RequestParam(defaultValue = "1") Integer page,
@RequestParam(defaultValue = "10") Integer pageSize,
${entityName} query) {
return Result.ok(${varName}Service.page(page, pageSize, query));
}

@GetMapping("/{id}")
public Result<${entityName}> get(@PathVariable String id) {
return Result.ok(${varName}Service.getById(id));
}

@PostMapping
public Result<Void> add(@RequestBody ${entityName} ${varName}) {
    ${varName}Service.save(${varName});
    return Result.ok();
    }

    @PutMapping
    public Result<Void> update(@RequestBody ${entityName} ${varName}) {
        ${varName}Service.updateById(${varName});
        return Result.ok();
        }

        @DeleteMapping("/{id}")
        public Result<Void> delete(@PathVariable String id) {
            ${varName}Service.removeById(id);
            return Result.ok();
            }
            }