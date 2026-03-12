package ${packageName}.service;

import ${packageName}.entity.${entityName};
import ${packageName}.mapper.${entityName}Mapper;
import ${packageName}.dao.${entityName}Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.ml.mldj.model.common.QueryCondition;
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
* 分页查询角色表
*/
public PageVO<${entityName}> page(PageQuery
<${entityName}Query> pageQuery) {
    Page<${entityName}> pageInfo = pageQuery.toPage();
    // 构建查询条件
    QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>();
    ${entityName}Query filters = pageQuery.getFilters();
    <#list columns as column>
        <#if column.showInSearch && column.queryOperator=="like">

            if(filters.get${column.columnName?cap_first}()!=null){
            queryWrapper.like("${column.columnName}",filters.get${column.columnName?cap_first}());
            }
        </#if>
        <#if column.showInSearch && column.queryOperator=="eq">



            if(filters.get${column.columnName?cap_first}()!=null){
            queryWrapper.eq("${column.columnName}",filters.get${column.columnName?cap_first}());

            }
        </#if>
        <#if column.showInSearch && column.queryOperator=="gte">


            if(filters.get${column.columnName?cap_first}()!=null){
            queryWrapper.ge("${column.columnName}",filters.get${column.columnName?cap_first}());
            }
        </#if>
        <#if column.showInSearch && column.queryOperator=="lte">

            if(filters.get${column.columnName?cap_first}()!=null){
            queryWrapper.le("${column.columnName}",filters.get${column.columnName?cap_first}());
            }
        </#if>

    </#list>


    // 构建排序条件
    if (pageQuery.hasSort()) {
    pageQuery.getSorts().forEach(e -> {
    String column = e.getUnderlineColumn();
    if (e.isAscending()) {
    queryWrapper.orderByAsc(column);
    } else {
    queryWrapper.orderByDesc(column);
    }
    });
    }
    // 分页查询
    Page<${entityName}> result =  ${entityName?uncap_first}Mapper.selectPage(pageInfo, queryWrapper);
    return PageVO.buildPageVO(result);
    }

    <#--                    /**-->
    <#--     * 分页查询${title}-->
    <#--     */-->
    <#--    public PageVO<${entityName}> page(Integer page, Integer pageSize<#if searchColumns?has_content>, <#list searchColumns as column>${column.javaType} ${column.fieldName}<#if column_has_next>, </#if></#list></#if>) {-->
    <#--        // 构建查询条件-->
    <#--        QueryWrapper<${entityName}> queryWrapper = new QueryWrapper<>();-->
    <#--        -->
    <#--<#if searchColumns?has_content>-->
    <#--<#list searchColumns as column>-->
    <#--        if (${column.fieldName} != null<#if column.javaType == "String"> && !${column.fieldName}.isEmpty()</#if>) {-->
    <#--    <#if column.javaType == "String">-->
    <#--            queryWrapper.like("${column.columnName}", ${column.fieldName});-->
    <#--    <#else>-->
    <#--            queryWrapper.eq("${column.columnName}", ${column.fieldName});-->
    <#--    </#if>-->
    <#--        }-->
    <#--</#list>-->
    <#--</#if>-->

    <#--        // 分页查询-->
    <#--        Page<${entityName}> pageInfo = new Page<>(page, pageSize);-->
    <#--        Page<${entityName}> result = ${entityName?uncap_first}Mapper.selectPage(pageInfo, queryWrapper);-->

    <#--        return PageVO.buildPageVO(result);-->
    <#--    }-->

    /**
    * 根据ID查询${title}
    */
    public ${entityName} getById(String id) {
    return ${entityName?uncap_first}Mapper.selectById(id);
    }

    /**
    * 新增${title}
    */
    public Long save(${entityName} ${entityName?uncap_first}) {
    ${entityName?uncap_first}Mapper.insert(${entityName?uncap_first});
    return ${entityName?uncap_first}.getId();
    }

    /**
    * 修改${title}
    */
    public void update(${entityName} ${entityName?uncap_first}) {
    ${entityName?uncap_first}Mapper.updateById(${entityName?uncap_first});
    }

    /**
    * 删除${title}
    */
    public void delete(String id) {
    ${entityName?uncap_first}Mapper.deleteById(id);
    }
    }