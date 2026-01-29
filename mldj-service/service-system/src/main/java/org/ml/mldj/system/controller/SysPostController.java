package org.ml.mldj.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.common.PageQuery;
import org.ml.mldj.model.common.PageVO;
import org.ml.mldj.model.system.dto.SysPostQuery;
import org.ml.mldj.model.system.entity.SysPost;
import org.ml.mldj.system.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author mailang
 * @since 2025-12-26
 */
@RestController
@RequestMapping("/sysPost")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;

    @Operation(summary = "获取分页列表")
    @PostMapping
    public Result<PageVO<SysPost>> findPage(@RequestBody PageQuery<SysPostQuery> sysPostQuery) {
        PageVO<SysPost> pageVO = sysPostService.page(sysPostQuery);
        return Result.success(pageVO);
    }

    @Operation(summary = "更新状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result<?> updateStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        boolean ok = sysPostService.updateStatus(id, status);
        return ok ? Result.success() : Result.fail();
    }

    @Operation(summary = "获取全部岗位")
    @GetMapping("/findAll")
    public List<SysPost> findAll() {
        return sysPostService.list();
    }

    @Operation(summary = "删除岗位")
    @DeleteMapping("/remove/{id}")
    public Result<?> remove(@PathVariable("id") Long id) {
        boolean ok = sysPostService.removeById(id);
        return ok ? Result.success() : Result.fail();
    }

    @Operation(summary = "获取")
    @GetMapping("/getById/{id}")
    public Result<SysPost> getById(@PathVariable("id") Long id) {
        SysPost post = sysPostService.getById(id);
        return Result.success(post);
    }

    @Operation(summary = "新增岗位")
    @PostMapping("/save")
    public Result<?> save(@RequestBody SysPost sysPost) {
        boolean ok = sysPostService.save(sysPost);
        return ok ? Result.success() : Result.fail();
    }

    @Operation(summary = "修改岗位")
    @PutMapping("/update")
    public Result<?> update(@RequestBody SysPost sysPost) {
        boolean ok = sysPostService.updateById(sysPost);
        return ok ? Result.success() : Result.fail();
    }
}
