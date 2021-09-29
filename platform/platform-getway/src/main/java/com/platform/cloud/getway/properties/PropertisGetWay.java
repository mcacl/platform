package com.platform.cloud.getway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2021/9/15 0015
 * 创建人:pmc
 * 描述:
 */
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "gateway")
public class PropertisGetWay{
    private PropertisTrace trace;
    private PropertisSecurity security;

    @Data
    public static class PropertisTrace{
        private Boolean enable = true;
    }

    @Data
    public static class PropertisSecurity{
        /**
         * 不过滤的路由
         */
        private List<String> excludeUrls = new ArrayList<>();
        private String key;
    }
}
