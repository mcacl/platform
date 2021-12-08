package com.platform.cloud.message.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 创建时间:2021/11/5 0005
 * 创建人:pmc
 * 描述:短信配置
 */
@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "message.sms")
public class PropertieSMS{
    /**
     * 签名
     */
    private String signCode;
}