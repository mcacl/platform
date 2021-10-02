package com.platform.cloud.common.core.aspect.notes;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.cloud.common.core.utils.UtilIP;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
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

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

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
    private final boolean PRINT_RESULT = false;//是否打印方法返回值

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
    public Object around(ProceedingJoinPoint joinPoint,Notes notes) throws Throwable{
        ApiOperation apiOperation = null;
        RequestMapping requestMapping = null;
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Annotation[] annotations = method.getAnnotations();

            for(Annotation annotation : annotations){
                if(annotation instanceof ApiOperation){
                    apiOperation = (ApiOperation) annotation;
                }
                //将查询出的多个annotationType类型注解属性合并到查询的第一个注解中
                requestMapping = AnnotatedElementUtils.findMergedAnnotation(method,RequestMapping.class);//找到controller方法
                if(apiOperation != null && requestMapping != null){
                    break;
                }
            }
            String methodDescription = "";
            //方法参数
            String logArgStr = "";
            if(ObjectUtils.isNotEmpty(apiOperation)){
                methodDescription = apiOperation.value();
            }else{
                methodDescription = notes.value();
            }
            if(ObjectUtils.isNotEmpty(requestMapping)){
                HttpServletRequest request = attributes.getRequest();
                logArgStr = getParamStr(joinPoint,true);//controller只取body参数
                printRequestBeforeLog(joinPoint,request,methodDescription,logArgStr,true);
            }else{
                logArgStr = getParamStr(joinPoint,false);
                this.printMethodBeforeLog(joinPoint,methodDescription,logArgStr,true);
            }
        } catch(Exception e){
            log.warn("Notes 日志打印异常");
        }
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long consuming = endTime - startTime;

        try{
            String resultStr = "不打印返回值";
            if(PRINT_RESULT){
                resultStr = objectMapper.writeValueAsString(result);
            }
            if(Objects.nonNull(requestMapping)){
                this.printRequestAfterLog(consuming,resultStr,true);
            }else{
                this.printMethodAfterLog(consuming,resultStr,true);
            }
        } catch(Exception var13){
            log.warn("@Weblog打印日志失败");
        }

        return result;
    }

    private void printRequestBeforeLog(JoinPoint joinPoint,HttpServletRequest request,String methodDescription,String requestArgsStr,boolean printArg){

        // 打印请求相关参数
        log.info("========================================== START ==========================================");
        // 打印请求 url
        log.info("[{}] URL>>{}",request.getMethod(),request.getRequestURL().toString());
        // 打印描述信息
        if(StringUtils.isNotBlank(methodDescription)){
            log.info("说明>>{}",methodDescription);
        }
        // 打印调用 controller 的全路径以及执行方法
        log.info("ClassMethod: {}#{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP>>{}",UtilIP.IPOrLocalIP(request.getRemoteAddr()));
        // 打印请求入参
        if(printArg && StringUtils.isNotBlank(requestArgsStr)){
            log.info("RequestArgs>>{}",requestArgsStr);
        }
    }

    private void printRequestAfterLog(long consuming,String resultStr,boolean printArg){
        log.info("用时>> {} ms",consuming);
        if(printArg && StringUtils.isNotBlank(resultStr)){
            log.info("Response返回>>  : {}",resultStr);
        }

        log.info("========================================== END ==========================================");
    }

    private void printMethodBeforeLog(JoinPoint joinPoint,String methodDescription,String logArgStr,boolean printArg){
        log.info("======= METHOD =======");
        if(StringUtils.isNotBlank(methodDescription)){
            log.info("说明>>{}",methodDescription);
        }
        log.info("ClassMethod: {}#{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        if(printArg && StringUtils.isNotBlank(logArgStr)){
            log.info("MethodArg: {}",logArgStr);
        }
    }

    private void printMethodAfterLog(long consuming,String resultStr,boolean printArg){
        log.info("用时>> {} ms",consuming);
        if(printArg){
            log.info("Method返回>>: {}",resultStr);
        }
        log.info("=========================================== END ===========================================");
    }

    private String getParamStr(JoinPoint joinPoint,boolean getBodyparam){
        String argsStr = "";
        try{
            Object[] args = getArgs(joinPoint,getBodyparam);
            if(ObjectUtils.isNotEmpty(args)){
                Map<String,Object> map = new HashMap<>();
                map.put("PARAM",args);
                argsStr = objectMapper.writeValueAsString(map);
            }

        } catch(Throwable e){
            log.error("Notes参数序列化错误!");
        }
        return argsStr;
    }

    /**
     * 获取切点参数
     * @param joinPoint 切点
     * @param getBodyparam 是否只取@RequestBody参数
     * @return
     */
    private Object[] getArgs(JoinPoint joinPoint,boolean getBodyparam){
        Object[] res = new Object[0];
        Object[] args = joinPoint.getArgs();
        if(getBodyparam){
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            Annotation[][] annotationsMethod = method.getParameterAnnotations();
            //找到参数个数与之匹配的方法(重载)
            for(int i = 0; i < annotationsMethod.length; i++){
                for(Annotation a : annotationsMethod[i]){
                    if(a instanceof RequestBody){
                        res = new Object[1];
                        res[0] = args[i];
                        return res;
                    }
                }
            }
        }else{
            res = args;
        }
        return res;
    }
}
