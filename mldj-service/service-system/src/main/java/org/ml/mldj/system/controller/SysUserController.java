package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysUserQuery;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器（对接 SysUserFeignClient）
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "保存用户")
    @PostMapping("/save")
    public Result<?> save(@RequestBody SysUser sysUser) {
        boolean ok = sysUserService.save(sysUser);
        return Result.success();
    }

    @Operation(summary = "更新用户")
    @PutMapping("/update")
    public Result<?> update(@RequestBody SysUser sysUser) {
        boolean ok = sysUserService.updateById(sysUser);
        return Result.success();
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result<?> remove(@PathVariable("id") Long id) {
        boolean ok = sysUserService.removeById(id);
        return Result.success();
    }

    @Operation(summary = "获取分页列表")
    @PostMapping
    public Result<PageVO<SysUser>> findPage(@RequestBody PageQuery<SysUserQuery> sysUserQuery) {
        PageVO<SysUser> pageVO = sysUserService.page(sysUserQuery);
        return Result.success(pageVO);
    }

    @Operation(summary = "更新状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        boolean ok = sysUserService.updateStatus(id, status);
        return Result.success();
    }

    @Operation(summary = "获取用户")
    @GetMapping("/getById/{id}")
    public Result<SysUser> getById(@PathVariable("id") Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.success(user);
    }

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/queryByUsername")
    public Result<SysUser> queryByUsername(@RequestParam("username") String username) {
        SysUser user = sysUserService.queryByUsername(username);
        return Result.success(user);
    }
}
