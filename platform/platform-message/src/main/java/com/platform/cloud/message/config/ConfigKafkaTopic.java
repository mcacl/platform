package com.platform.cloud.message.config;

import com.platform.cloud.common.message.constant.ConstantTopic;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * 创建时间:2021/12/8 0008
 * 创建人:pmc
 * 描述:
 */
@Configuration
public class ConfigKafkaTopic{
    /** 新建主题 */
    @Bean
    public NewTopic loginLog(){
        return TopicBuilder.name(ConstantTopic.TOPIC_LOG).partitions(1).replicas(1)
                // 清理策略
                .config(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE)
                // 一天后清理
                .config(TopicConfig.DELETE_RETENTION_MS_CONFIG,"86400000")
                // 压缩类型 [uncompressed, zstd, lz4, snappy, gzip, producer]
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG,"lz4").build();
    }
}