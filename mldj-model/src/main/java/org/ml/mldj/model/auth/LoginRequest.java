package org.ml.mldj.model.auth;

import lombok.Data;

import java.util.List;

@Data
public class LoginRequest {
    String username;
    String password;
}
