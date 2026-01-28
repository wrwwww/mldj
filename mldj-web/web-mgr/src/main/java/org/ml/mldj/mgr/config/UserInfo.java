package org.ml.mldj.mgr.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String userId;
    private String username;
    private String email;
    private String phone;
    private List<String> roles;
    private List<String> permissions;
    private Map<String, Object> attributes;

    // 从 Authentication 构建
    public static UserInfo fromAuthentication(Authentication authentication) {
        if (authentication == null) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(authentication.getName());

        // 处理权限
        userInfo.setRoles(
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(auth -> auth.startsWith("ROLE_"))
                        .map(auth -> auth.substring(5))
                        .collect(Collectors.toList())
        );

        userInfo.setPermissions(
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(auth -> !auth.startsWith("ROLE_"))
                        .collect(Collectors.toList())
        );

        // 处理 JWT
//        if (authentication.getPrincipal() instanceof Jwt jwt) {
//            userInfo.setUserId(jwt.getSubject());
//            userInfo.setEmail(jwt.getClaimAsString("email"));
//            userInfo.setPhone(jwt.getClaimAsString("phone"));
//
//            // 自定义属性
//            Map<String, Object> attrs = new HashMap<>();
//            attrs.put("department", jwt.getClaim("department"));
//            attrs.put("tenantId", jwt.getClaim("tenant_id"));
//            userInfo.setAttributes(attrs);
//        }
//
        return userInfo;
    }
}