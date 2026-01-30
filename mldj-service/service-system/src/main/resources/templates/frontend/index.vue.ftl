<#assign entityName = config.entityName>
<#assign routePath = config.routePath>
<#assign searchCols = config.columns?filter(c -> c.showInSearch)!>
<#assign tableCols = config.columns?filter(c -> c.showInList)!>

<script setup lang="tsx">
    import type { ProDataTableColumns, ProSearchFormColumns } from 'pro-naive-ui'
    import type { SetOptional } from 'type-fest'
    import type { ListSearchParams, ${entityName} } from './index.api'
    import { Icon } from '@iconify/vue'
    import { createProModalForm, createProSearchForm, renderProDateText } from 'pro-naive-ui'
    import { computed } from 'vue'
    import { useProNDataTable } from '@/composables/use-pro-n-data-table'
    import { useProRequest } from '@/composables/use-pro-request'
    import { $t } from '@/locales/locales'
    import EntityModalForm from './components/${entityName?uncap_first}-modal-form.vue'
    import { Api } from './index.api'

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
        { form: searchForm },
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

    const { run: runDelete } = useProRequest(Api.del, {
        manual: true,
        successTip: 'common.often.deleteSuccess',
        onSuccess() { onChange({ page: 1 }) },
    })

    const { run: handleEdit } = useProRequest(Api.get, {
        manual: true,
        onSuccess: ({ data }) => {
            modalForm.show.value = true
            modalForm.values.value = data
        },
    })

    const searchColumns = computed<ProSearchFormColumns<ListSearchParams>>(() => [
        { title: $t('common.often.index'), type: 'index' },
        <#list searchCols as c>
        {
            title: '${c.label}',
            path: '${c.columnName?replace("_([a-z])", "$1", "r")?uncap_first}',
        },
        </#list>
    ])

    const tableColumns = computed<ProDataTableColumns<${entityName}>>(() => [
        { title: $t('common.often.index'), type: 'index' },
        <#list tableCols as c>
        <#assign path = c.columnName?replace("_([a-z])", "$1", "r")?uncap_first>
        <#assign width = c.listWidth!120>
        <#if c.columnType?lower_case?contains("date") || c.columnType?lower_case?contains("time")>
        {
            title: '${c.label}',
            path: '${path}',
            width: ${width},
            render: row => renderProDateText(row.${path}),
        },
        <#else>
        {
            title: '${c.label}',
            path: '${path}',
            width: ${width},
        },
        </#if>
        </#list>
        {
            title: $t('common.often.operation'),
            width: 120,
            render: (row) => (
                <n-flex>
                    <n-button type="primary" size="small" text onClick={() => handleEdit(row.id)}>
                        {$t('common.often.edit')}
                    </n-button>
                    <n-popconfirm onPositiveClick={() => runDelete(row.id)}>
                        {{
                            default: () => $t('common.often.deleteConfirm'),
                            trigger: () => (
                                <n-button type="error" size="small" text>
                                    {$t('common.often.delete')}
                                </n-button>
                            ),
                        }}
                    </n-popconfirm>
                </n-flex>
            ),
        },
    ])
</script>

<template>
    <n-flex class="h-full" vertical size="large">
        <pro-card content-class="pb-0!">
            <pro-search-form
                    :form="searchForm"
                    :columns="searchColumns"
                    v-bind="proSearchFormProps"
            />
        </pro-card>
        <pro-data-table
                :title="$t('pages.system.${routePath}.title')"
                row-key="id"
                :scroll-x="1200"
                :columns="tableColumns"
                v-bind="tableProps"
        >
            <template #toolbar>
                <n-button type="primary" ghost @click="modalForm.show.value = true">
                    <template #icon>
                        <n-icon><icon icon="ant-design:plus-outlined" /></n-icon>
                    </template>
                    {{ $t('common.often.add') }}
                </n-button>
            </template>
        </pro-data-table>
        <pro-modal-form
                :form="modalForm"
                :loading="insertOrUpdateLoading"
                :title="modalForm.values.value.id ? $t('common.often.edit') : $t('common.often.add')"
        >
            <EntityModalForm />
        </pro-modal-form>
    </n-flex>
</template>