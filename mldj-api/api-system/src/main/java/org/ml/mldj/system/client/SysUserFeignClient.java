package org.ml.mldj.system.client;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysUserQuery;
import org.ml.mldj.model.system.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient("service-system")
public interface SysUserFeignClient {
    @Operation(summary = "保存用户")
    @PostMapping("/sysUser/save")
    Result<?> save(@RequestBody SysUser sysUser);

    @Operation(summary = "更新用户")
    @PutMapping("/sysUser/update")
    Result<?> update(@RequestBody SysUser sysUser);

    @Operation(summary = "删除用户")
    @DeleteMapping("/sysUser/remove/{id}")
    Result<?> remove(@PathVariable("id") Long id);

    @Operation(summary = "获取分页列表")
    @PostMapping("/sysUser")
    Result<PageVO<SysUser>> findPage(
            @RequestBody PageQuery<SysUserQuery> sysUserQuery
    );

    @Operation(summary = "更新状态")
    @GetMapping("/sysUser/updateStatus/{id}/{status}")
    Result<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status);

    @Operation(summary = "获取用户")
    @GetMapping("/sysUser/getById/{id}")
    Result<SysUser> getById(@PathVariable("id") Long id);

    // web-mgr 暂无对应路由；先按函数名约定一个常用查询接口
    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/sysUser/queryByUsername")
    Result<SysUser> queryByUsername(@RequestParam("username") String username);
}
