package org.ml.mldj.system.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.RequiredArgsConstructor;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.GenConfig;
import org.ml.mldj.system.mapper.GenConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;


@Service
@RequiredArgsConstructor
public class GenConfigService extends ServiceImpl<GenConfigMapper, GenConfig> {

    private final Configuration freeMarkerConfig;
    private final GenConfigMapper genConfigMapper;

    public PageVO<GenConfig> page(PageQuery<GenConfig> query) {
        IPage<GenConfig> mpPage = new Page<>(query.getPageNum(), query.getPageSize());
        LambdaQueryWrapper<GenConfig> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(query.getFilters().getDatasourceId()), GenConfig::getDatasourceId, query.getFilters().getDatasourceId())
                .like(StringUtils.hasText(query.getFilters().getTableName()), GenConfig::getTableName, query.getFilters().getTableName());
        genConfigMapper.selectPage(mpPage, queryWrapper);

        return PageVO.buildPageVO(mpPage);
    }

    public Map<String, Object> generateCode(String configId) {
        GenConfig config = getById(configId);
        if (config == null) {
            throw new RuntimeException("生成配置不存在");
        }

        Map<String, Object> result = new HashMap<>();

        try {
            // 生成前端代码
            result.put("frontend", generateFrontend(config));

            // 生成后端代码
            result.put("backend", generateBackend(config));

            // 生成SQL
//            result.put("sql", generateSql(config));

        } catch (Exception e) {
            throw new RuntimeException("代码生成失败: " + e.getMessage(), e);
        }

        return result;
    }

    private Map<String, String> generateFrontend(GenConfig config) throws Exception {
        Map<String, String> frontend = new HashMap<>();

        // 设置模板数据
        Map<String, Object> data = new HashMap<>();
        data.put("config", config);
        data.put("entityName", config.getEntityName());
        data.put("routePath", config.getRoutePath());
        data.put("moduleName", config.getModuleName());

        // 生成 index.vue
        Template indexTemplate = freeMarkerConfig.getTemplate("vue-index.ftl");
        frontend.put("index.vue", renderTemplate(indexTemplate, data));

        // 生成 index.api.ts
        Template apiTemplate = freeMarkerConfig.getTemplate("vue-api.ftl");
        frontend.put("index.api.ts", renderTemplate(apiTemplate, data));

        // 生成 modal-form.vue
        Template formTemplate = freeMarkerConfig.getTemplate("vue-modal-form.ftl");
        frontend.put("modal-form.vue", renderTemplate(formTemplate, data));

        Template typesTemplate = freeMarkerConfig.getTemplate("vue-types.ftl");
        frontend.put("types.vue", renderTemplate(typesTemplate, data));

        return frontend;
    }

    private Map<String, String> generateBackend(GenConfig config) throws Exception {
        Map<String, String> backend = new HashMap<>();

        Map<String, Object> data = new HashMap<>();
        data.put("config", config);
        data.put("entityName", config.getEntityName());
        data.put("moduleName", config.getModuleName());
        data.put("title", config.getTitle());
        data.put("routePath", config.getRoutePath());
        data.put("packageName", config.getPackageName());

        // 生成 Controller.java
        Template controllerTemplate = freeMarkerConfig.getTemplate("controller.ftl");
        backend.put("Controller.java", renderTemplate(controllerTemplate, data));

        // 生成 Service.java
        Template serviceTemplate = freeMarkerConfig.getTemplate("service.ftl");
        backend.put("Service.java", renderTemplate(serviceTemplate, data));

        // 生成 Entity.java
        Template entityTemplate = freeMarkerConfig.getTemplate("entity.ftl");
        backend.put("Entity.java", renderTemplate(entityTemplate, data));
        // 生成mapper.java
        Template mapperTemplate = freeMarkerConfig.getTemplate("mapper.ftl");
        backend.put("Mapper.java", renderTemplate(mapperTemplate, data));

        return backend;
    }

    private String generateSql(GenConfig config) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("config", config);
        data.put("tableName", config.getTableName());

        Template sqlTemplate = freeMarkerConfig.getTemplate("sql.ftl");
        return renderTemplate(sqlTemplate, data);
    }

    private String renderTemplate(Template template, Map<String, Object> data) throws Exception {
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        return writer.toString();
    }
}