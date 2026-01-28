package org.ml.mldj.model.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String username;
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
}
