package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysDept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-system")
public interface SysDeptFeignClient {
    @Operation(summary = "获取全部部门节点")
    @GetMapping("/sysDept/findNodes")
    Result<List<SysDept>> findNodes();

    @Operation(summary = "获取用户部门节点")
    @GetMapping("/sysDept/findUserNodes")
    Result<List<SysDept>> findUserNodes();

    @Operation(summary = "更新状态")
    @GetMapping("/sysDept/updateStatus/{id}/{status}")
    Result<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status);

    @Operation(summary = "获取")
    @GetMapping("/sysDept/getById/{id}")
    Result<SysDept> getById(@PathVariable("id") Long id);

    @Operation(summary = "新增")
    @PostMapping("/sysDept/save")
    Result<?> save(@RequestBody SysDept sysDept);

    @Operation(summary = "修改")
    @PutMapping("/sysDept/update")
    Result<?> update(@RequestBody SysDept sysDept);

    @Operation(summary = "删除")
    @DeleteMapping("/sysDept/remove/{id}")
    Result<?> remove(@PathVariable("id") Long id);
}
