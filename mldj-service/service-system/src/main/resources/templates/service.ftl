package ${packageName}.service;

import ${packageName}.entity.${entityName};
import ${packageName}.mapper.${entityName}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * ${title} Service
 * 
 * @author CodeGenerator
 * @date ${.now?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
public class ${entityName}Service {

    @Autowired
    private ${entityName}Mapper ${entityName?uncap_first}Mapper;

    /**
     * 分页查询${title}
     */
    public PageResult<${entityName}> page(Integer page, Integer pageSize<#if searchColumns?has_content>, <#list searchColumns as column>${column.javaType} ${column.fieldName}<#if column_has_next>, </#if></#list></#if>) {
        // 构建查询条件
        QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>();
        
<#if searchColumns?has_content>
<#list searchColumns as column>
        if (${column.fieldName} != null<#if column.javaType == "String"> && !${column.fieldName}.isEmpty()</#if>) {
    <#if column.javaType == "String">
            queryWrapper.like("${column.columnName}", ${column.fieldName});
    <#else>
            queryWrapper.eq("${column.columnName}", ${column.fieldName});
    </#if>
        }
</#list>
</#if>
        
        // 分页查询
        Page<${entityName}> pageInfo = new Page<>(page, pageSize);
        Page<${entityName}> result = ${entityName?uncap_first}Mapper.selectPage(pageInfo, queryWrapper);
        
        return new PageResult<>(result.getRecords(), result.getTotal(), page, pageSize);
    }

    /**
     * 根据ID查询${title}
     */
    public ${entityName} getById(String id) {
        return ${entityName?uncap_first}Mapper.selectById(id);
    }

    /**
     * 新增${title}
     */
    public String save(${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}.setId(UUID.randomUUID().toString());
        ${entityName?uncap_first}.setCreateTime(LocalDateTime.now());
        ${entityName?uncap_first}.setUpdateTime(LocalDateTime.now());
        ${entityName?uncap_first}Mapper.insert(${entityName?uncap_first});
        return ${entityName?uncap_first}.getId();
    }

    /**
     * 修改${title}
     */
    public void update(${entityName} ${entityName?uncap_first}) {
        ${entityName?uncap_first}.setUpdateTime(LocalDateTime.now());
        ${entityName?uncap_first}Mapper.updateById(${entityName?uncap_first});
    }

    /**
     * 删除${title}
     */
    public void delete(String id) {
        ${entityName?uncap_first}Mapper.deleteById(id);
    }
}