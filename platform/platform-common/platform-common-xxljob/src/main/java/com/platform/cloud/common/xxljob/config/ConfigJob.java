package com.platform.cloud.common.xxljob.config;

import com.platform.cloud.common.xxljob.properties.PropertiXxlJob;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

/**
 * 创建时间:2021/12/14 0014
 * 创建人:pmc
 * 描述:
 */
@Slf4j
@Configuration
@RefreshScope
@ConditionalOnClass(XxlJobSpringExecutor.class)
public class ConfigJob{
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(PropertiXxlJob propertiXxlJob){
        log.info(">>>>>>>>>>> xxl-job 分布式任务调度初始化");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        Optional.ofNullable(propertiXxlJob.getAccessToken()).ifPresent(xxlJobSpringExecutor::setAccessToken);
        if(propertiXxlJob.getAdmin() != null){
            Optional.ofNullable(propertiXxlJob.getAdmin().getAddresses()).ifPresent(xxlJobSpringExecutor::setAdminAddresses);
        }
        if(propertiXxlJob.getExecutor() != null){
            Optional.ofNullable(propertiXxlJob.getExecutor().getAppName()).ifPresent(xxlJobSpringExecutor::setAppname);
            Optional.ofNullable(propertiXxlJob.getExecutor().getIp()).ifPresent(xxlJobSpringExecutor::setIp);
            Optional.ofNullable(propertiXxlJob.getExecutor().getPort()).ifPresent(xxlJobSpringExecutor::setPort);
            Optional.ofNullable(propertiXxlJob.getExecutor().getLogPath()).ifPresent(xxlJobSpringExecutor::setLogPath);
            Optional.ofNullable(propertiXxlJob.getExecutor().getLogRetentionDays()).ifPresent(xxlJobSpringExecutor::setLogRetentionDays);
        }
        return xxlJobSpringExecutor;
    }
}