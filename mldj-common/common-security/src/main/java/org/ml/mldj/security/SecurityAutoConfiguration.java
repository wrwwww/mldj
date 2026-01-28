package org.ml.mldj.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityAutoConfiguration {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        return new JwtAuthenticationFilter(jwtTokenUtil);
    }
}
