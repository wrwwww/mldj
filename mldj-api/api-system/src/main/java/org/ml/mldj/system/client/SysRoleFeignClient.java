package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysRoleQuery;
import org.ml.mldj.model.system.entity.SysRole;
import org.ml.mldj.model.system.vo.AssginRoleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("service-system")
public interface SysRoleFeignClient {
    @Operation(summary = "获取")
    @GetMapping("/sysRole/getById/{id}")
    Result<SysRole> getById(@PathVariable("id") Long id);

    @Operation(summary = "新增角色")
    @PostMapping("/sysRole/save")
    Result<?> save(@RequestBody SysRole sysRole);

    @Operation(summary = "修改角色")
    @PutMapping("/sysRole/update")
    Result<?> update(@RequestBody SysRole sysRole);

    @Operation(summary = "删除角色")
    @DeleteMapping("/sysRole/remove/{id}")
    Result<?> remove(@PathVariable("id") Long id);

    @Operation(summary = "获取分页列表")
    @PostMapping("/sysRole")
    Result<PageVO<SysRole>> findPage(
            @RequestBody PageQuery<SysRoleQuery> roleQuery
    );

    @Operation(summary = "根据id列表删除")
    @DeleteMapping("/sysRole/batchRemove")
    Result<?> batchRemove(@RequestBody List<Long> idList);

    @Operation(summary = "根据用户分配角色")
    @PostMapping("/sysRole/doAssign")
    Result<?> doAssign(@RequestBody AssginRoleVo assginRoleVo);

    @Operation(summary = "获取全部角色列表")
    @GetMapping("/sysRole/findAll")
    List<SysRole> findAll();

    @Operation(summary = "根据用户获取角色数据")
    @GetMapping("/sysRole/toAssign/{userId}")
    Result<Map<String, Object>> toAssign(@PathVariable("userId") Long userId);
}
