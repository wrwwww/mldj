package org.ml.mldj.mgr.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.service.SysUserService;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.order.vo.OrderVO;
import org.ml.mldj.model.system.dto.SysUserQuery;
import org.ml.mldj.model.system.entity.SysUser;
import org.ml.mldj.order.client.OrderFeignClient;
import org.ml.mldj.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Tag(name = "用户管理")
@RestController
@RequestMapping("/sysUser")
@Slf4j
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "获取分页列表")
    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @PostMapping("{page}/{limit}")
    public Result<PageVO<SysUser>> findPage(
            @Parameter(name = "userQuery", description = "查询对象", required = false)
            @RequestBody PageQuery<SysUserQuery> sysUserQuery) {
        return sysUserService.findPage(sysUserQuery);
    }

    @Operation(summary = "获取用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    @GetMapping("getById/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return sysUserService.getById(id);
    }

    @Operation(summary = "保存用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.add')")
    @PostMapping("save")
    public Result<?> save(@RequestBody SysUser sysUser) {
//        sysUser.setPassword(MD5.encrypt(sysUser.getPassword()));
        return sysUserService.save(sysUser);
    }

    @Operation(summary = "更新用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.update')")
    @PutMapping("update")
    public Result<?> update(@RequestBody SysUser sysUser) {
        return sysUserService.update(sysUser);
    }

    @Operation(summary = "删除用户")
    @PreAuthorize("hasAuthority('bnt.sysUser.remove')")
    @DeleteMapping("remove/{id}")
    public Result<?> remove(@PathVariable Long id) {
        return sysUserService.remove(id);
    }

    @Operation(summary = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public Result<?> updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        return sysUserService.updateStatus(id, status);
    }

    @Autowired
    OrderFeignClient orderFeignClient;
    @GetMapping("/list")
    public Result<?> list() {
        Long userId = SecurityUtils.getUserId();
        log.debug("当前用户id:{}", userId);
        Result<OrderVO> query = orderFeignClient.query("1");
        return Result.success(new ArrayList<SysUser>());
    }
}

