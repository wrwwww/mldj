package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysPostQuery;
import org.ml.mldj.model.system.entity.SysPost;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-system")
public interface SysPostFeignClient {
    @Operation(summary = "获取分页列表")
    @PostMapping("/sysPost")
    Result<PageVO<SysPost>> findPage(@RequestBody PageQuery<SysPostQuery> sysPostQuery);

    @Operation(summary = "更新状态")
    @GetMapping("/sysPost/updateStatus/{id}/{status}")
    Result<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status);

    @Operation(summary = "获取全部岗位")
    @GetMapping("/sysPost/findAll")
    List<SysPost> findAll();

    @Operation(summary = "删除岗位")
    @DeleteMapping("/sysPost/remove/{id}")
    Result<?> remove(@PathVariable("id") Long id);

    @Operation(summary = "获取")
    @GetMapping("/sysPost/getById/{id}")
    Result<SysPost> getById(@PathVariable("id") Long id);

    @Operation(summary = "新增岗位")
    @PostMapping("/sysPost/save")
    Result<?> save(@RequestBody SysPost sysPost);

    @Operation(summary = "修改岗位")
    @PutMapping("/sysPost/update")
    Result<?> update(@RequestBody SysPost sysPost);
}
