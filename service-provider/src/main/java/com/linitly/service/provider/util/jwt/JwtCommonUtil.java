package com.linitly.service.provider.util.jwt;

import com.linitly.service.provider.util.BeanUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author linxiunan
 * @Description JWT加密工具类
 * @date 2018年12月25日
 */
public class JwtCommonUtil {

    private static final String UUID_JWT = "UUID";

    /**
     * @author linxiunan
     * @date 2019/8/13 19:19
     * @return java.util.Date
     * @description jwt设置失效时间
     */
    public static Date getExpireDateTime(Long expireTime) {
        return new Date(System.currentTimeMillis() + expireTime);
    }

    /**
     * @author linxiunan
     * @date 2019/8/13 19:18
     * @return java.security.Key
     * @description 根据加密密钥和加密算法生成KEY
     */
    public static Key getKeyInstance(String salt, SignatureAlgorithm algorithm) {
        // Base64编码后的secretKey
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(salt);
        Key secretKey = new SecretKeySpec(apiKeySecretBytes, algorithm.getJcaName());
        return secretKey;
    }

    /**
     * @author linxiunan
     * @date 2019/8/13 17:55
     * @description 解码jwt，返回解密后的数据，如果jwt为空，则返回null
     */
    public static Map<String, Object> parseJwt(String jwt, String salt, SignatureAlgorithm algorithm) {
        if (StringUtils.isBlank(jwt)) {
            return null;
        }
        Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance(salt, algorithm)).parseClaimsJws(jwt).getBody();
        return jwtClaims;
    }

    /**
     * @author linxiunan
     * @date 2019/8/13 17:57
     * @return java.lang.String
     * @description 生成jwt，需传入需要加密的信息，加密密钥(盐)，加密方法和过期时间，加密密钥(盐)和加密算法如果为空会抛出运行时异常
     */
    public static String generateJwt(Map<String, Object> claims, String salt, SignatureAlgorithm algorithm, Long expireTime) {
        validJwtParams(salt, algorithm, expireTime);
        if (claims == null) claims = new HashMap<>();
        Date expirationDate = getExpireDateTime(expireTime);
        claims.put(UUID_JWT, UUID.randomUUID().toString());
        String compactJws = Jwts.builder()
                .setSubject(UUID_JWT)
                .setExpiration(expirationDate)
                .setClaims(claims)
                .signWith(algorithm, getKeyInstance(salt, algorithm))
                .compact();
        return compactJws;
    }

    /**
     * @author linxiunan
     * @date 2019/8/13 17:57
     * @return java.lang.String
     * @description 生成jwt，需传入需要加密的信息，加密密钥(盐)，加密方法和过期时间，加密密钥(盐)和加密算法如果为空会抛出运行时异常
     */
    public static String generateJwt(Object obj, String salt, SignatureAlgorithm algorithm, Long expireTime) {
        validJwtParams(salt, algorithm, expireTime);
        if (obj == null) {
            throw new RuntimeException("obj mast not null");
        }
        Map<String, Object> claims = BeanUtils.object2Map(obj);
        Date expirationDate = getExpireDateTime(expireTime);
        claims.put(UUID_JWT, UUID.randomUUID().toString());
        String compactJws = Jwts.builder()
                .setSubject(UUID_JWT)
                .setExpiration(expirationDate)
                .setClaims(claims)
                .signWith(algorithm, getKeyInstance(salt, algorithm))
                .compact();
        return compactJws;
    }

    private static void validJwtParams(String salt, SignatureAlgorithm algorithm, Long expireTime) {
        if (expireTime == null) {
            throw new RuntimeException("expireTime mast not null");
        }
        if (StringUtils.isBlank(salt)) {
            throw new RuntimeException("salt mast not null");
        }
        if (algorithm == null) {
            throw new RuntimeException("algorithm mast not null");
        }
    }
}
