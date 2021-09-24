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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
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
            do401(httpServletResponse,message);
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
            do401(httpServletResponse,message);
            return;
        }
        // 分离TTL
        String[] split = secretAndTTL.split("#");
        String secret = split[0];
        String ttlStr = split[1];
        int ttl = Integer.parseInt(ttlStr);
        // 验证jwt
        boolean verify = UtilsJWT.verify(secret,jwt);
        if(!verify){
            String message = StrFormatter.format("Token验证失败! jwt:{},userId:{}",jwt,userId);
            log.info(message);
            do401(httpServletResponse,message);
            return;
        }
        /*AdminUser adminUser = adminUserService.getById(userId);

        List<AdminMenu> adminMenuByUserId =  Optional.ofNullable(adminMenuService.getAdminMenuByUserId(userId))
                .orElseGet(Lists::emptyList);
        List<AdminAuthority> menuAuthorities = adminMenuByUserId
                .stream()
                .filter(adminMenu -> Objects.equals(adminMenu.getType(), 2))
                .map(AdminAuthority::new)
                .collect(Collectors.toList());*/

        // 更新response中的Jwt
        flushJwt(secret,userId,ttlStr,httpServletResponse);
        bucket.expire(ttl,TimeUnit.SECONDS);

        // 存入SecurityContext
        //var authentication = new AdminJwtAuthentication(jwt, secret, adminUser, menuAuthorities);
        SecurityContext context = SecurityContextHolder.getContext();
        //context.setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void flushJwt(String secretKey,String userId,String ttl,HttpServletResponse response){
        String jwt = UtilsJWT.generateToken(secretKey,builder->builder.withClaim("userId",userId));
        response.setHeader(HttpHeaders.AUTHORIZATION,ConstantSecurity.MANAGER_HEADER_PREFIX + jwt);
        response.setHeader(HttpHeaders.EXPIRES,ttl);
    }

    private void do401(HttpServletResponse response,String message){
        try{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            PrintWriter writer = response.getWriter();
            // 返回resp
            // KResponse<Object> failed = KResponse.failed();
            // failed.setStatusMessage(message);
            // failed.setStatusCode(1);
            // 写入body
            // writer.write(JSONObject.toJSONString(failed));
            // writer.flush();
        } catch(IOException e){
            log.error("检查token返回401时发生异常",e);
        }
    }
}
