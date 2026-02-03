<script setup lang="ts">
import type { ${entityName} } from '../types'

defineOptions({ name: '${entityName}ModalForm' })
</script>

<template>
<#list formColumns as column>
<#if column.formType == "input">
  <pro-input title="${column.label}" path="${column.fieldName}"<#if column.required> required</#if> />
<#elseif column.formType == "textarea">
  <pro-textarea title="${column.label}" path="${column.fieldName}"<#if column.required> required</#if> />
<#elseif column.formType == "select">
  <pro-select title="${column.label}" path="${column.fieldName}"<#if column.required> required</#if> :field-props="{
    options: [
      // TODO: 配置选项
    ],
  }" />
<#elseif column.formType == "date">
  <pro-date-picker title="${column.label}" path="${column.fieldName}"<#if column.required> required</#if> />
<#elseif column.formType == "datetime">
  <pro-date-time-picker title="${column.label}" path="${column.fieldName}"<#if column.required> required</#if> />
<#else>
  <pro-input title="${column.label}" path="${column.fieldName}"<#if column.required> required</#if> />
</#if>
</#list>
</template>