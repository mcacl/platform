package com.platform.cloud.message.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 创建时间:2021/12/8 0008
 * 创建人:pmc
 * 描述:手动处理监听错误
 */
@Slf4j
@Component
public class KafkaErrorHandler implements ConsumerAwareListenerErrorHandler{
    @Override
    public Object handleError(Message<?> message,ListenerExecutionFailedException e,Consumer<?,?> consumer){
        //可以把错误记录到表中
        log.error("消息消费发生异常:",e);
        return "消息消费发生异常!";
    }
}