package org.ml.mldj.system.controller;



import lombok.RequiredArgsConstructor;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.Datasource;
import org.ml.mldj.model.system.GenConfig;
import org.ml.mldj.model.system.TableMeta;
import org.ml.mldj.system.service.DatasourceService;
import org.ml.mldj.system.service.GenConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器控制器
 */
@RestController
@RequestMapping("/code-gen")
@RequiredArgsConstructor
public class CodeGenController {

    private final DatasourceService datasourceService;
    private final GenConfigService genConfigService;

    // ========== 数据源管理 ==========

    @GetMapping("/datasource/page")
    public Result<PageVO<Datasource>> datasourcePage(PageQuery<Datasource> query) {
        return Result.success(datasourceService.page(query));
    }

    @GetMapping("/datasource/list")
    public Result<List<Datasource>> datasourceList() {
        return Result.success(datasourceService.list());
    }

    @GetMapping("/datasource/{id}")
    public Result<Datasource> getDatasource(@PathVariable String id) {
        return Result.success(datasourceService.getById(id));
    }

    @PostMapping("/datasource")
    public Result<Void> saveDatasource(@RequestBody Datasource datasource) {
        datasourceService.saveOrUpdate(datasource);
        return Result.success();
    }

    @DeleteMapping("/datasource/{id}")
    public Result<Void> deleteDatasource(@PathVariable String id) {
        datasourceService.removeById(id);
        return Result.success();
    }

    @PostMapping("/datasource/{id}/test")
    public Result<Boolean> testDatasource(@PathVariable String id) {
        boolean success = datasourceService.testConnection(id);
        return Result.success(success);
    }

    @GetMapping("/datasource/{id}/tables")
    public Result<List<TableMeta>> getTables(@PathVariable String id) {
        return Result.success(datasourceService.getTables(id));
    }

    // ========== 生成配置管理 ==========

    @GetMapping("/config/page")
    public Result<PageVO<GenConfig>> configPage(
            PageQuery<GenConfig> query) {
        return Result.success(genConfigService.page(query));
    }

    @GetMapping("/config/list")
    public Result<List<GenConfig>> configList() {
        return Result.success(genConfigService.list());
    }

    @GetMapping("/config/{id}")
    public Result<GenConfig> getConfig(@PathVariable String id) {
        return Result.success(genConfigService.getById(id));
    }

    @PostMapping("/config")
    public Result<Void> saveConfig(@RequestBody GenConfig config) {
        genConfigService.saveOrUpdate(config);
        return Result.success();
    }

    @DeleteMapping("/config/{id}")
    public Result<Void> deleteConfig(@PathVariable String id) {
        genConfigService.removeById(id);
        return Result.success();
    }

    @GetMapping("/config/{id}/generate")
    public Result<Map<String, Object>> generateCode(@PathVariable String id) {
        return Result.success(genConfigService.generateCode(id));
    }
}