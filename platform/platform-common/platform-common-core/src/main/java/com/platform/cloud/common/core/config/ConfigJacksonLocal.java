package com.platform.cloud.common.core.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 创建时间:2021/11/2 0002
 * 创建人:pmc
 * 描述:自定义序列化
 */
@Configuration
public class ConfigJacksonLocal{
    /**
     * description:适配自定义序列化和反序列化策略
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder->{
            builder.serializerByType(LocalDateTime.class,new LocalDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class,new LocalDateTimeDeserializer());
        };
    }

    /**
     * description:LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     * 接收毫秒级时间戳字符串——>LocalDateTime
     */
    @Bean
    public Converter<Long,LocalDateTime> localDateTimeConverter(){
        return new Converter<Long,LocalDateTime>(){
            @Override
            public LocalDateTime convert(Long source){
                //毫秒级时间戳转LocalDateTime
                return LocalDateTimeUtil.of(source,ZoneOffset.systemDefault());
            }
        };
    }

    /**
     * description:序列化
     * LocalDateTime序列化为毫秒级时间戳
     */
    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime>{
        @Override
        public void serialize(LocalDateTime value,JsonGenerator gen,SerializerProvider serializers) throws IOException{
            if(value != null){
                long timestamp = LocalDateTimeUtil.toEpochMilli(value);
                gen.writeNumber(timestamp);
            }
        }
    }

    /**
     * description:反序列化
     * 毫秒级时间戳序列化为LocalDateTime
     */
    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>{
        @Override
        public LocalDateTime deserialize(JsonParser p,DeserializationContext deserializationContext) throws IOException{
            long timestamp = p.getValueAsLong();
            if(timestamp > 0){
                return LocalDateTimeUtil.of(timestamp,ZoneOffset.systemDefault());
            }else{
                return null;
            }
        }
    }
}