package org.ml.mldj.auth.controller;

import org.ml.mldj.auth.service.AuthService;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.model.auth.LoginRequest;
import org.ml.mldj.model.auth.LoginResponse;
import org.ml.mldj.model.system.entity.SysMenu;
import org.ml.mldj.security.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody LoginRequest req) {
        return Result.success(authService.login(req));
    }
    @GetMapping("/user/info")
    public Result<Map<String,Object>> info(){
        Long userId = SecurityUtils.getUserId();
        return Result.success(authService.userInfo(userId));
    }
    @GetMapping("/menu/list")
//    public Result<List<SysMenu>> list(){
//        Long userId = SecurityUtils.getUserId();
//        return Result.success(authService.list(userId));
//    }
    public Result<?> list(){
        Long userId = SecurityUtils.getUserId();
        return Result.success(authService.userInfo(userId).get("menus"));
    }


}
