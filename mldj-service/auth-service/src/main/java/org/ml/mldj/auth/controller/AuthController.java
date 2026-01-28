package org.ml.mldj.auth.controller;

import org.ml.mldj.auth.service.AuthService;
import org.ml.mldj.model.auth.LoginRequest;
import org.ml.mldj.model.auth.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
