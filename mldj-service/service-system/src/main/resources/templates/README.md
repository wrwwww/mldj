# 代码生成模板说明

本目录包含了用于代码生成的 FreeMarker 模板文件，支持生成完整的前后端代码。

## 模板文件说明

### 后端模板
- `controller.ftl` - Spring Boot Controller 控制器模板
- `service.ftl` - Service 业务逻辑层模板  
- `entity.ftl` - JPA/MyBatis 实体类模板
- `mapper.ftl` - MyBatis Mapper 接口模板

### 前端模板
- `vue-index.ftl` - Vue 3 主页面模板
- `vue-api.ftl` - API 接口调用模板
- `vue-types.ftl` - TypeScript 类型定义模板
- `vue-modal-form.ftl` - 表单弹窗组件模板

### 数据库模板
- `sql.ftl` - 数据库建表 SQL 模板

## 模板变量说明

### 基础变量
- `${packageName}` - Java 包名
- `${moduleName}` - 模块名（如 system）
- `${entityName}` - 实体类名（大驼峰，如 User）
- `${title}` - 功能标题（如 用户管理）
- `${routePath}` - 路由路径（如 user）
- `${tableName}` - 数据库表名

### 列相关变量
- `${columns}` - 所有列信息数组
- `${listColumns}` - 列表显示的列
- `${searchColumns}` - 搜索条件的列
- `${formColumns}` - 表单编辑的列

### 列对象属性
- `${column.columnName}` - 数据库列名
- `${column.fieldName}` - Java 字段名（小驼峰）
- `${column.javaType}` - Java 类型
- `${column.tsType}` - TypeScript 类型
- `${column.comment}` - 列注释
- `${column.label}` - 显示标签
- `${column.formType}` - 表单控件类型
- `${column.required}` - 是否必填
- `${column.primaryKey}` - 是否主键
- `${column.listWidth}` - 列表宽度

## 使用方式

1. 在前端页面配置好数据源、选择表、配置列信息
2. 点击"生成前后端代码与 SQL"按钮
3. 后端会使用这些模板生成对应的代码文件
4. 生成的文件会保存到指定目录

## 自定义模板

可以根据项目需要修改这些模板文件，支持完整的 FreeMarker 语法：

- 条件判断：`<#if condition>...</#if>`
- 循环遍历：`<#list items as item>...</#list>`
- 变量输出：`${variable}`
- 内置函数：`${.now?string("yyyy-MM-dd")}`

更多 FreeMarker 语法请参考官方文档。