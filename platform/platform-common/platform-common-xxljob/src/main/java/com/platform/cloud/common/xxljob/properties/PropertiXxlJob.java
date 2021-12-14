package com.platform.cloud.common.xxljob.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 创建时间:2021/12/14 0014
 * 创建人:pmc
 * 描述:
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxljob")
@RefreshScope
public class PropertiXxlJob{
    private AdminProperties admin;

    private ExecutorProperties executor;

    private String accessToken;

    @Data
    public static class AdminProperties{
        private String addresses;
    }

    @Data
    public static class ExecutorProperties{
        private String ip;

        private Integer port;

        private String appName;

        private String logPath;

        private Integer logRetentionDays;
    }
}