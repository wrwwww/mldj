package org.ml.mldj.auth.config;

import org.ml.mldj.security.JsonAccessDeniedHandler;
import org.ml.mldj.security.JsonAuthenticationEntryPoint;
import org.ml.mldj.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtFilter, JsonAccessDeniedHandler jsonAccessDeniedHandler, JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/v3/api-docs").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling((exceptionHandling) ->
                        exceptionHandling.accessDeniedHandler(jsonAccessDeniedHandler)
                                .authenticationEntryPoint(jsonAuthenticationEntryPoint)
                ).sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
