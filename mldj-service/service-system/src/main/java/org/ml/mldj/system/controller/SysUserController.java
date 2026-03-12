package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysUserQuery;
import org.ml.mldj.model.system.dto.SysUserUpdateForm;
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
@RequestMapping("/system/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "获取分页列表")
    @GetMapping("/page")
    public Result<PageVO<SysUser>> page(PageQuery<SysUserQuery> sysUserQuery) {
        PageVO<SysUser> pageVO = sysUserService.page(sysUserQuery);
        return Result.success(pageVO);
    }

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/queryByUsername")
    public Result<SysUser> queryByUsername(@RequestParam("username") String username) {
        SysUser user = sysUserService.queryByUsername(username);
        return Result.success(user);
    }

    /**
     * 根据ID查询用户表
     */
    @GetMapping("/{id}")
    public Result<SysUser> getById(@PathVariable String id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.success(sysUser);
    }

    /**
     * 新增用户表
     */
    @PostMapping
    public Result<String> save(@RequestBody SysUserUpdateForm sysUser) {
        sysUserService.save(sysUser);
        return Result.success();
    }

    /**
     * 修改用户表
     */
    @PutMapping
    public Result<?> update(@RequestBody SysUserUpdateForm sysUser) {
        sysUserService.update(sysUser);
        return Result.success();
    }

    /**
     * 删除用户表
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable String id) {
        sysUserService.delete(id);
        return Result.success();
    }
}
