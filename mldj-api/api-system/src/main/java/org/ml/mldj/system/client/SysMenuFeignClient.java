package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.model.system.vo.AssginMenuVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("service-system")
public interface SysMenuFeignClient {
    @Operation(summary = "给角色分配权限")
    @PostMapping("/sysMenu/doAssign")
    Result<?> doAssign(@RequestBody AssginMenuVo assginMenuVo);

    @Operation(summary = "删除菜单")
    @DeleteMapping("/sysMenu/remove/{id}")
    Result<?> remove(@PathVariable("id") Long id);

    @Operation(summary = "新增菜单")
    @PostMapping("/sysMenu/save")
    Result<?> save(@RequestBody SysMenu sysMenu);

    @Operation(summary = "修改菜单")
    @PutMapping("/sysMenu/update")
    Result<?> update(@RequestBody SysMenu sysMenu);

    @Operation(summary = "获取菜单")
    @GetMapping("/sysMenu/findNodes")
    List<SysMenu> findNodes();

    @Operation(summary = "根据角色获取菜单")
    @GetMapping("/sysMenu/toAssign/{roleId}")
    Result<?> toAssign(@PathVariable("roleId") Long roleId);
}
