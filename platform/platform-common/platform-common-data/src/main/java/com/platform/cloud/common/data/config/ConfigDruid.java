package com.platform.cloud.common.data.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建时间:2021/10/8 0008
 * 创建人:pmc
 * 描述:
 */
@Configuration
public class ConfigDruid{
    @Bean
    public Slf4jLogFilter logFilter(){
        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setStatementExecutableSqlLogEnable(true);
        logFilter.setStatementLogEnabled(true);
        return logFilter;
    }

    /**
     * sql防火墙过滤器配置
     */
    @Bean
    public WallFilter wallFilter(WallConfig wallConfig){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        wallFilter.setLogViolation(true);//对被认为是攻击的SQL进行LOG.error输出
        wallFilter.setThrowException(false);//对被认为是攻击的SQL抛出SQLException
        return wallFilter;
    }

    /**
     * sql防火墙配置
     */
    @Bean
    public WallConfig wallConfig(){
        WallConfig wallConfig = new WallConfig();
        wallConfig.setAlterTableAllow(false);
        wallConfig.setCreateTableAllow(false);
        wallConfig.setDescribeAllow(false);
        wallConfig.setShowAllow(false);
        wallConfig.setMergeAllow(false);
        wallConfig.setDeleteAllow(true);
        return wallConfig;
    }
}
