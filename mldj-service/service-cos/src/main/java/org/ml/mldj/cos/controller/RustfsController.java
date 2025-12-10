package org.ml.mldj.cos.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class RustfsController {

    @PostMapping("/uploadfile")
    public String uploadFile(@Param("file") MultipartFile file, @Param("path") String path) {
        if (file.isEmpty()) {

        }



    }
}
