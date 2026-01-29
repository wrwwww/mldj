package org.ml.mldj.system.config;

import org.ml.mldj.security.JsonAccessDeniedHandler;
import org.ml.mldj.security.JsonAuthenticationEntryPoint;
import org.ml.mldj.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                    JwtAuthenticationFilter jwtFilter, JsonAccessDeniedHandler jsonAccessDeniedHandler, JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint) throws Exception {


        // 禁用 CSRF（REST API 通常不需要）
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling((exceptionHandling) ->
                        exceptionHandling.accessDeniedHandler(jsonAccessDeniedHandler)
                                .authenticationEntryPoint(jsonAuthenticationEntryPoint)
                ).sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }
}

