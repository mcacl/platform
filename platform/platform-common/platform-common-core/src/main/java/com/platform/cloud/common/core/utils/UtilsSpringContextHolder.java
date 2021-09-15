package com.platform.cloud.common.core.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 创建时间:2021/9/7
 * 创建人:pmc
 * 描述:spring 容器bean获取工具类
 */
@Slf4j
@Service
@Lazy(false)
public class UtilsSpringContextHolder implements ApplicationContextAware, DisposableBean{
    private static ApplicationContext applicationContext = null;
    @Getter
    private static String applicationName = null;
    @Getter
    private static boolean isDebug = true;
    @Getter
    private static boolean isDev = true;
    private static Integer serverPort = null;

    public static Integer getServerPort(){
        return serverPort;
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    @Override
    public void destroy() throws Exception{
        UtilsSpringContextHolder.clearHolder();//清理
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
        UtilsSpringContextHolder.applicationContext = applicationContext;
        UtilsSpringContextHolder.applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        UtilsSpringContextHolder.isDebug = Objects.equals(applicationContext.getEnvironment().getProperty("debug"),"true");

        UtilsSpringContextHolder.isDev = Stream.of(applicationContext.getEnvironment().getActiveProfiles()).anyMatch(s->!s.equals("prod"));

        String serverPortStr = applicationContext.getEnvironment().getProperty("server.port");
        Optional.ofNullable(serverPortStr).ifPresent(s->UtilsSpringContextHolder.serverPort = Integer.valueOf(s));
        log.info("当前注册中心:{}",applicationContext.getEnvironment().getProperty("spring.cloud.nacos.server-addr"));
    }

    /**
     * 获取容器中的bean
     * @param name bean name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

    /**
     * 更具类型取bean
     * @param requiredType class
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder(){
        if(log.isDebugEnabled()){
            log.debug("清理SpringContextHolder");
        }
        UtilsSpringContextHolder.applicationContext = null;
        UtilsSpringContextHolder.applicationName = null;
        UtilsSpringContextHolder.isDev = true;
        UtilsSpringContextHolder.isDebug = true;
        UtilsSpringContextHolder.serverPort = null;
    }

    /**
     * 发布事件
     * @param event
     */
    public static void publishEvent(ApplicationEvent event){
        if(applicationContext == null){
            return;
        }
        applicationContext.publishEvent(event);
    }
}