package com.platform.cloud.common.core.aspect.notes;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 创建时间:2021/9/29 0029
 * 创建人:pmc
 * 描述:
 */
@Aspect
@Component
@Slf4j
public class AspectNotes{
    public static final ObjectMapper objectMapper;
    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    static{
        objectMapper = new ObjectMapper();
        objectMapper.setLocale(Locale.CHINA);
        objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    /**
     * @param notes
     * @notes 做切点
     */
    @Pointcut(value = "@annotation(notes)", argNames = "notes")
    public void notesPoint(Notes notes){ }

    @Around(value = "notesPoint(notes)", argNames = "joinPoint,notes")
    public void around(ProceedingJoinPoint joinPoint,Notes notes){

        try{

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Annotation[] annotations = method.getAnnotations();
            ApiOperation apiOperation = null;
            RequestMapping requestMapping = null;
            for(Annotation annotation : annotations){
                if(annotation instanceof ApiOperation){
                    apiOperation = (ApiOperation) annotation;
                }
                requestMapping = AnnotatedElementUtils.findMergedAnnotation(method,RequestMapping.class);
                if(apiOperation != null && requestMapping != null){
                    break;
                }
            }

        } catch(Exception e){
            log.warn("Notes 日志打印异常");
        }
    }

    private String getArgs(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Annotation[][] annotationsMethod = method.getParameterAnnotations();
        for(int i = 0; i < annotationsMethod.length; i++){
            for(Annotation a : annotationsMethod[i]){
                if(a instanceof RequestBody){
                    try{
                        return objectMapper.writeValueAsString(args[i]);
                    } catch(JsonProcessingException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}
