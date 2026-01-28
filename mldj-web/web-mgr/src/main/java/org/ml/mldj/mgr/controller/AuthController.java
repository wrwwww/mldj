package org.ml.mldj.mgr.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.exception.BizException;
import org.ml.mldj.common.utils.JwtTokenUtil;
import org.ml.mldj.common.utils.Result;
import org.ml.mldj.mgr.config.LoginUser;
import org.ml.mldj.model.system.dto.LoginForm;
import org.ml.mldj.model.system.vo.LoginVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<?> login(@Valid @RequestBody LoginForm request) {
        try {
            // 1. 认证用户名和密码
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            // 2. 设置认证信息到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 3. 生成 JWT Token
            LoginUser userDetails = (LoginUser) authentication.getPrincipal();
            String token = jwtTokenUtil.generateAccessToken(userDetails.getUserId(), userDetails.getUsername());
            // 4. 返回响应
            LoginVO response = LoginVO.builder()
                    .accessToken(token)
                    .tokenType("Bearer")
                    .expiresIn(jwtTokenUtil.getExpiration())
                    .username(userDetails.getUsername())
//                    .authorities(authentication.getAuthorities())
                    .build();
            return Result.success(response);
        } catch (BadCredentialsException e) {
            log.warn("登录失败: 用户名或密码错误 - {}", request.getUsername());
            throw new BizException("用户名或密码错误");
        } catch (DisabledException e) {
            throw new BizException("账户已被禁用");
        } catch (LockedException e) {
            throw new BizException("账户已被锁定");
        } catch (Exception e) {
            log.error("登录异常", e);
            throw new BizException("系统错误，请稍后重试");
        }
    }

    @GetMapping("/test")
    public Result<?> test(){
        return Result.success("test");
    }
//    /**
//     * 用户注册
//     */
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
//        // 注册逻辑...
//        return ResponseEntity.ok("注册成功");
//    }
//
//    /**
//     * 刷新 Token
//     */
//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) {
//        // 刷新 Token 逻辑...
//        return ResponseEntity.ok("新的 Token");
//    }
//
//    /**
//     * 获取当前用户信息
//     */
//    @GetMapping("/me")
//    public ResponseEntity<?> getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        Map<String, Object> userInfo = new HashMap<>();
//        userInfo.put("username", authentication.getName());
//        userInfo.put("authorities", authentication.getAuthorities());
//        userInfo.put("authenticated", authentication.isAuthenticated());
//
//        return ResponseEntity.ok(userInfo);
//    }
//
//    /**
//     * 退出登录
//     */
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//        // 清除 SecurityContext
//        SecurityContextHolder.clearContext();
//
//        // 清除 Session（如果有）
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//
//        // 清除 Cookie（如果有）
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                cookie.setMaxAge(0);
//                cookie.setValue(null);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//            }
//        }
//
//        return ResponseEntity.ok(Map.of("message", "退出成功"));
//    }
}