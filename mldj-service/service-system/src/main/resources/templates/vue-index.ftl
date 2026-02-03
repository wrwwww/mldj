<script setup lang="tsx">
import type { ProDataTableColumns } from 'pro-naive-ui'
import type { SetOptional } from 'type-fest'
import type { ${entityName} } from './types'
import { Icon } from '@iconify/vue'
import { createProModalForm, createProSearchForm } from 'pro-naive-ui'
import { useProNDataTable, useProRequest } from '@/composables'
import ${entityName}ModalForm from './components/${entityName?uncap_first}-modal-form.vue'
import { Api } from './index.api'

defineOptions({ name: '${entityName}Page' })

const searchForm = createProSearchForm()

const {
  loading: insertOrUpdateLoading,
  runAsync: runAsyncInsertOrUpdate,
} = useProRequest(Api.insertOrUpdate, {
  manual: true,
  successTip: true,
})

const {
  search: { proSearchFormProps },
  table: { tableProps, onChange },
} = useProNDataTable(
  async ({ current, pageSize }, values) => {
    const res = await Api.page({ pageSize, page: current, ...values })
    return res.data
  },
  {
    form: searchForm,
  },
)

const modalForm = createProModalForm<SetOptional<${entityName}, 'id'>>({
  onSubmit: (values) => {
    runAsyncInsertOrUpdate({
      ...values,
      id: modalForm.values.value.id,
    }).then(() => {
      modalForm.show.value = false
      onChange({ page: 1 })
    })
  },
})

const { run: runDel } = useProRequest(Api.del, {
  manual: true,
  successTip: true,
  onSuccess: () => onChange({ page: 1 }),
})

const {
  loading: getByIdLoading,
  run: runGetById,
} = useProRequest(Api.getById, {
  manual: true,
  onSuccess: ({ data: ${entityName?uncap_first} }) => {
    modalForm.show.value = true
    modalForm.values.value = ${entityName?uncap_first}
  },
})

const searchColumns = [
<#list searchColumns as column>
  {
    title: '${column.label}',
    key: '${column.fieldName}',
    field: '${column.formType}',
  },
</#list>
]

const tableColumns: ProDataTableColumns<${entityName}> = [
<#list listColumns as column>
  {
    title: '${column.label}',
    key: '${column.fieldName}',
<#if column.listWidth??>
    width: ${column.listWidth},
</#if>
<#if column.javaType == "String">
    ellipsis: {
      tooltip: true
    }
</#if>
  },
</#list>
  {
    title: '操作',
    key: 'actions',
    width: 200,
    fixed: 'right',
    render: (row) => (
      <n-flex size="small">
        <n-button
          size="small"
          text
          loading={getByIdLoading.value}
          onClick={() => runGetById(row.id)}
        >
          编辑
        </n-button>
        <n-popconfirm onPositiveClick={() => runDel(row.id)}>
          {{
            default: () => '确定删除该${title}？',
            trigger: () => (
              <n-button size="small" type="error" text>
                删除
              </n-button>
            ),
          }}
        </n-popconfirm>
      </n-flex>
    ),
  },
]
</script>

<template>
  <n-flex
    class="h-full"
    vertical
    size="large"
  >
    <pro-card content-class="pb-0!">
      <pro-search-form
        :form="searchForm"
        :columns="searchColumns"
        v-bind="proSearchFormProps"
      />
    </pro-card>
    <pro-data-table
      :title="'${title}'"
      row-key="id"
      :scroll-x="1200"
      :columns="tableColumns"
      v-bind="tableProps"
    >
      <template #toolbar>
        <n-flex>
          <n-button
            type="primary"
            ghost
            @click="modalForm.show.value = true"
          >
            <template #icon>
              <n-icon>
                <icon icon="ant-design:plus-outlined" />
              </n-icon>
            </template>
            新增${title}
          </n-button>
        </n-flex>
      </template>
    </pro-data-table>
    <pro-modal-form
            :form="modalForm"
            :loading="insertOrUpdateLoading"
            :title="modalForm.values.value.id ? `编辑${title}` : `新增${title}`"
    >
      <${entityName?uncap_first}-modal-form />
    </pro-modal-form>
  </n-flex>
</template>