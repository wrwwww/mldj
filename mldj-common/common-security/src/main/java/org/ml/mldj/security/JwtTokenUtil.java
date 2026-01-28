package org.ml.mldj.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ml.mldj.common.exception.TokenException;
import org.ml.mldj.common.utils.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

import static org.ml.mldj.security.TokenConst.CLAIM_KEY_ROLES;
import static org.ml.mldj.security.TokenConst.CLAIM_KEY_USERID;

@Component
@Slf4j
@Getter
public class JwtTokenUtil {

    private static final String ISSUER = "your-app";
    private static final String AUDIENCE = "web-client";
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "iat";
    private static final String CLAIM_KEY_TYPE = "type";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:86400000}")
    private long expiration;

    @Value("${jwt.refresh-expiration:604800000}")
    private long refreshExpiration;

    private Key key;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        // 使用更安全的密钥生成方式
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        // 预构建JWT解析器，提高性能
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(ISSUER)
                .requireAudience(AUDIENCE)
                .build();
    }

    /**
     * 生成访问令牌
     */
    public String generateAccessToken(LoginUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERID, userDetails.getUserId());
//        claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList()));
        claims.put(CLAIM_KEY_TYPE, "access");

        return buildToken(claims, userDetails.getUsername(), expiration);
    }

    public String generateAccessToken(long id,String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERID, id);
        claims.put(CLAIM_KEY_TYPE, "access");

        return buildToken(claims, username, expiration);
    }

    /**
     * 生成刷新令牌
     */
    public String generateRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_TYPE, "refresh");

        return buildToken(claims, username, refreshExpiration);
    }

    private String buildToken(Map<String, Object> claims, String subject, long expiration) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer(ISSUER)
                .setAudience(AUDIENCE)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setId(UUID.randomUUID().toString())  // 添加JWT ID防止重放攻击
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从令牌中获取用户ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get(CLAIM_KEY_USERID, Long.class);
    }

    /**
     * 从令牌中获取用户名
     */
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * 从令牌中获取签发时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getAllClaimsFromToken(token).getIssuedAt();
    }

    /**
     * 从令牌中获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    /**
     * 从令牌中获取用户角色
     */
    public List<String> getRolesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get(CLAIM_KEY_ROLES, List.class);
    }

    /**
     * 获取令牌类型（access/refresh）
     */
    public String getTokenType(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get(CLAIM_KEY_TYPE, String.class);
    }

    /**
     * 解析令牌中的所有claims（已做异常处理）
     */
    public Claims getAllClaimsFromToken(String token) {
        try {
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.warn("JWT令牌已过期: {}", e.getMessage());
            throw new TokenException(ResultCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的JWT令牌: {}", e.getMessage());
            throw new TokenException(ResultCode.TOKEN_INVALID);
        } catch (MalformedJwtException e) {
            log.error("JWT令牌格式错误: {}", e.getMessage());
            throw new TokenException(ResultCode.TOKEN_MALFORMED);
        } catch (SignatureException e) {
            log.error("JWT签名验证失败: {}", e.getMessage());
            throw new TokenException(ResultCode.TOKEN_SIGNATURE);
        } catch (IllegalArgumentException e) {
            log.error("JWT令牌参数错误: {}", e.getMessage());
            throw new TokenException(ResultCode.TOKEN_ILLEGALARGUMENT);
        }
    }
    public boolean validateToken(String token) {
        try {
            getAllClaimsFromToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    /**
     * 验证令牌是否有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = getUsernameFromToken(token);
            final String tokenType = getTokenType(token);

            return (username.equals(userDetails.getUsername())
                    && !isTokenExpired(token)
                    && "access".equals(tokenType));
        } catch (TokenException e) {
            return false;
        }
    }

    /**
     * 验证刷新令牌
     */
    public boolean validateRefreshToken(String token) {
        try {
            return !isTokenExpired(token) && "refresh".equals(getTokenType(token));
        } catch (TokenException e) {
            return false;
        }
    }

    /**
     * 检查令牌是否过期
     */
    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 判断令牌在指定时间内是否刚刚刷新过
     */
    public boolean isTokenJustRefreshed(String token, long refreshWindow) {
        final Date issuedAt = getIssuedAtDateFromToken(token);
        final Date now = new Date();
        return (issuedAt.getTime() + refreshWindow) > now.getTime();
    }

    /**
     * 生成包含设备信息的令牌
     */
//    public String generateDeviceToken(String username, String deviceId, String deviceType) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("device_id", deviceId);
//        claims.put("device_type", deviceType);
//        claims.put("ip", getClientIp());
//
//        return generateAccessToken(claims, username);
//    }

    /**
     * 生成包含自定义claims的令牌
     */
    public String generateAccessToken(Map<String, Object> additionalClaims, String username) {
        Map<String, Object> claims = new HashMap<>(additionalClaims);
        claims.put(CLAIM_KEY_TYPE, "access");

        return buildToken(claims, username, expiration);
    }
}