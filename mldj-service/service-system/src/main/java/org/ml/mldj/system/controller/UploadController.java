package org.ml.mldj.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.dto.*;
import org.ml.mldj.model.vo.UploadInitResp;
import org.ml.mldj.system.service.MultipartUploadService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {


    @Autowired
    private MultipartUploadService uploadService;

    @PostMapping("/init")
    public Result<UploadInitResp> init(@RequestBody UploadInitReq req) {
        return Result.success(uploadService.init(req));
    }

    @GetMapping("/presign")
    public Result<String> presign(
            @RequestParam String objectKey,
            @RequestParam String uploadId,
            @RequestParam Integer partNumber
    ) {
        return Result.success(uploadService.presign(objectKey, uploadId, partNumber));
    }

    @GetMapping("/list")
    public Result<List<UploadedPartVO>> list(
            @RequestParam String objectKey,
            @RequestParam String uploadId
    ) {
        return Result.success(uploadService.listParts(objectKey, uploadId));
    }

    @PostMapping("/complete")
    public Result<?> complete(@RequestBody CompleteUploadReq req) {
        uploadService.complete(req.getObjectKey(), req.getUploadId());
        return Result.success();
    }

    @PostMapping("/abort")
    public Result<?> abort(@RequestBody AbortUploadReq req) {
        uploadService.abort(req.getObjectKey(), req.getUploadId());
        return Result.success();
    }
}
