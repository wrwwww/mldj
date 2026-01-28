package org.ml.mldj.model.system.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;


@Getter
@Setter
@Builder
public class LoginVO {
//    private String userId;
    private String username;
    private String accessToken;
    private String tokenType;
    private Long expiresIn;
    private Collection<Object>    authorities;
}
