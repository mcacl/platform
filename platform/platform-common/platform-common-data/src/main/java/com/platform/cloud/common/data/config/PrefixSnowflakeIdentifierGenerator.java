package com.platform.cloud.common.data.config;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:雪花id 生成
 */
public class PrefixSnowflakeIdentifierGenerator implements IdentifierGenerator{
    @Override
    public Number nextId(Object entity){
        return null;
    }

    @Override
    public String nextUUID(Object entity){
        return IdentifierGenerator.super.nextUUID(entity);
    }
}
