package com.platform.cloud.common.core.constant;

/**
 * 创建时间:2021/9/22
 * 创建人:pmc
 * 描述:常量
 */
public class ConstantSecurity{
    /**
     * manager head
     */
    public static final String MANAGER_HEADER_PREFIX = "JwtMAN";

    public static final String PORTAL_HEADER_PREFIX = "Jwt ";

    /**
     * RedisKey-jwt 与用户绑定的secret
     */
    public static final String REDIS_JWT_USER_SECRET = "jwtSecret:";
}
