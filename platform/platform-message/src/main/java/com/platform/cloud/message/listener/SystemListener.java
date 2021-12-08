package com.platform.cloud.message.listener;

import com.platform.cloud.common.message.constant.ConstantTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * 创建时间:2021/12/8 0008
 * 创建人:pmc
 * 描述:消息消费
 */
@Slf4j
@Component
public class SystemListener{

    @KafkaListener(topics = {ConstantTopic.TOPIC_LOG}, errorHandler = "kafkaErrorHandler")
    public void messagePhoneSMS(@Payload String msg){
        //msg序列化 然后使用
    }
}