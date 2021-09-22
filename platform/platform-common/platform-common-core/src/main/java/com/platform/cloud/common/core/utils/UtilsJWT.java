package com.platform.cloud.common.core.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.TextEncryptor;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * 创建时间:2021/9/22
 * 创建人:pmc
 * 描述:
 */
@Slf4j
public class UtilsJWT{
    private static final String ISSUE = "Issue"; //签发

    /**
     * 生成签名
     */
    public static String generateToken(String secretKey,Consumer<Builder> customClaim){
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(secretKey); //256

        JWTCreator.Builder builder = JWT.create().withIssuer(ISSUE).withIssuedAt(now);

        customClaim.accept(builder);
        return builder.sign(algorithm);
    }

    public static Claim getClaimWithBearerBean(String token,String claim){
        try{
            return JWT.decode(token).getClaim(claim);
        } catch(JWTDecodeException ex){
            log.warn("JWT get claim failed! [claim:{},token:{}]",claim,token);
            return null;
        }
    }

    private static TextEncryptor getTextEncryptor(String secret){
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secret);
        return encryptor;
    }

    public static String generateTokenWithBearerBean(String secretKey,Object bearerBean,Consumer<JWTCreator.Builder> customClaim){
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTCreator.Builder builder = JWT.create().withIssuer(ISSUE).withIssuedAt(now);
        String bearer = JSON.toJSONString(bearerBean);
        TextEncryptor encryptor = getTextEncryptor(secretKey);
        String encryptBearer = encryptor.encrypt(bearer);

        String bearerClazz = bearerBean.getClass().getName();
        builder.withClaim("bearer",encryptBearer);
        builder.withClaim("bearerClazz",bearerClazz);

        Optional.ofNullable(customClaim).ifPresent(builderConsumer->customClaim.accept(builder));
        return builder.sign(algorithm);
    }

    public static Object getBearerBeanWithToken(String secretKey,String token){
        String encryptBearer = "";
        String bearerClazz = "";
        try{
            DecodedJWT decodedJWT = JWT.decode(token);
            encryptBearer = decodedJWT.getClaim("bearer").asString();
            bearerClazz = decodedJWT.getClaim("bearerClazz").asString();

            Class<?> aClass = Class.forName(bearerClazz);
            TextEncryptor encryptor = getTextEncryptor(secretKey);
            String bearer = encryptor.decrypt(encryptBearer);

            return JSON.parseObject(bearer,aClass);

        } catch(JWTDecodeException ex){
            log.warn("JWT get claim[bearer,bearerClazz] failed! [claim1:{},claim2:{},token:{}]",encryptBearer,bearerClazz,token);
            return null;
        } catch(ClassNotFoundException e){
            log.warn("JWT get claim failed! Because class not found![class:{},token:{}]",bearerClazz,token);
            return null;
        }
    }

    /**
     * 验证token
     */
    public static boolean verify(String secretKey,String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUE).build();
            verifier.verify(token);
            return true;
        } catch(Exception ex){
            return false;
        }
    }

    /**
     * 从token获取userId
     */
    public static Claim getClaim(String token,String claim){
        try{
            return JWT.decode(token).getClaim(claim);
        } catch(JWTDecodeException ex){
            log.warn("JWT get claim failed! [claim:{},token:{}]",claim,token);
            return null;
        }
    }
}
