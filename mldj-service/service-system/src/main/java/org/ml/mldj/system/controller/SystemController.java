package org.ml.mldj.system.controller;

import org.ml.mldj.common.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对接 SystemFeignClient 的系统通用能力接口
 */
@RestController
@RequestMapping("/ci")
public class SystemController {

    /**
     * 文本审核占位实现：目前直接回显内容
     */
    @PostMapping("/textAuditing")
    public Result<String> textAuditing(@RequestBody String content) {
        return Result.success(content);
    }
}
