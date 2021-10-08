package com.platform.cloud.common.data.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDateTime;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Configuration
public class ConfigMybatisPlus{

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        //内部乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     * @return 雪花id生成
     */
    @Bean
    @Primary
    public IdentifierGenerator prefixSnowflakeIdGenerator(){
        return new PrefixSnowflakeIdentifierGenerator();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler(){
            @Override
            public void insertFill(MetaObject metaObject){
                log.debug("开始插入数据前填充制定数据----");
                strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
                strictInsertFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject){
                log.debug("开始更新数据前填充制定数据----");
                this.strictUpdateFill(metaObject,"updateTime",LocalDateTime.class,LocalDateTime.now());
            }
        };
    }
}
