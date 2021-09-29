package com.platform.cloud.common.core.aspect.notes;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

    }
}
