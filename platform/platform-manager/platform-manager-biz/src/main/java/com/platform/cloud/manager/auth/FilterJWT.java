package com.platform.cloud.manager.auth;

import cn.hutool.core.text.StrFormatter;
import com.auth0.jwt.interfaces.Claim;
import com.platform.cloud.common.core.constant.ConstantSecurity;
import com.platform.cloud.common.core.utils.UtilsJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.http.HttpHeaders;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 创建时间:2021/9/22
 * 创建人:pmc
 * 描述:
 */
@Slf4j
public class FilterJWT extends OncePerRequestFilter{
    private final RedissonClient redissonClient;

    public FilterJWT(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,FilterChain filterChain) throws ServletException, IOException{
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = Stream.of("/**/login","/**/logout").anyMatch(m->antPathMatcher.match(m,httpServletRequest.getServletPath().toLowerCase()));
        if(match){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        String head = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isNotBlank(head) || !head.startsWith(ConstantSecurity.MANAGER_HEADER_PREFIX)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        // 取出jwt
        String jwt = head.replace(ConstantSecurity.MANAGER_HEADER_PREFIX,"");
        Claim userIdClaim = UtilsJWT.getClaim(jwt,"userId");
        if(Objects.isNull(userIdClaim)){
            String message = StrFormatter.format("错误的Token! jwt:{}!",jwt);
            log.warn(message);
            do401(response,message);
            return;
        }
        String userId = userIdClaim.asString();
        // 取出secret
        String redisKey = ConstantSecurity.REDIS_JWT_USER_SECRET + userId;
        RBucket<String> bucket = redissonClient.getBucket(redisKey);
        String secretAndTTL = bucket.get();
        if(Objects.isNull(secretAndTTL)){
            String message = StrFormatter.format("Redis中未找到secret! redisKey:{}",redisKey);
            log.debug(message);
            do401(response,message);
            return;
        }
    }

}
