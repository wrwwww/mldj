package org.ml.mldj.auth.service;

import org.ml.mldj.model.auth.LoginRequest;
import org.ml.mldj.model.auth.LoginResponse;
import org.ml.mldj.security.JwtTokenUtil;
import org.ml.mldj.security.LoginUser;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(UserDetailsService uds,
                       PasswordEncoder passwordEncoder,
                       JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = uds;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public LoginResponse login(LoginRequest req) {

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(req.getUsername());

        if (!passwordEncoder.matches(req.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) userDetails;

        String token = jwtTokenUtil.generateAccessToken(loginUser);
        return
                LoginResponse.builder().accessToken(token)
                        .tokenType("Bearer")
                        .expiresIn(jwtTokenUtil.getExpiration())
                        .username(userDetails.getUsername())
//                    .authorities(authentication.getAuthorities())
                        .build();
    }

}
