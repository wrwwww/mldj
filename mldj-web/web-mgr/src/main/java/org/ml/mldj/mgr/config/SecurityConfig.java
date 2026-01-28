package org.ml.mldj.mgr.config;

import org.ml.mldj.common.utils.JwtTokenUtil;
import org.ml.mldj.mgr.filter.JwtAuthenticationFilter;
import org.ml.mldj.mgr.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    // 通过构造函数注入
    public SecurityConfig(JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 禁用 CSRF（REST API 通常不需要）
                .csrf(AbstractHttpConfigurer::disable)
                // 配置权限规则
                .authorizeHttpRequests(auth -> auth
                        // 公开接口（不需要认证）
                        .requestMatchers(
                                "/auth/login",           // 登录接口
                                "/auth/test",           // 登录接口
                                "/auth/register",        // 注册接口
                                "/auth/refresh-token",   // 刷新token
                                "/api/public/**",        // 所有公开API
                                "/swagger-ui/**",        // Swagger UI
                                "/v3/api-docs/**",       // OpenAPI 文档
                                "/swagger-ui.html",      // Swagger HTML
                                "/webjars/**",           // Swagger 静态资源
                                "/doc.html",             // Knife4j 文档
                                "/actuator/**",          // Actuator 端点
                                "/actuator/health",      // 健康检查
                                "/nacos/**",             // Nacos 相关
                                "/error"                 // 错误页面
                        ).permitAll()

                        // 需要特定角色的接口
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")

                        // 其他所有请求都需要认证
                        .anyRequest().authenticated()
                )

                // 添加 JWT 过滤器
                .addFilterBefore(jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    // 定义 jwtAuthenticationFilter Bean
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenUtil, userDetailsService);
    }


    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 认证管理器
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
