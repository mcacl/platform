package com.platform.cloud.common.core.entity.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/12/2 0002
 * 创建人:pmc
 * 描述:基础键值实体
 */
@Data
public class BaseKeyValue{
    @ApiModelProperty(value = "键Long")
    private Long key;
    @ApiModelProperty("键String")
    private String code;
    @ApiModelProperty("值")
    private String name;

    public BaseKeyValue(){
    }

    public BaseKeyValue(Long key,String name){
        this.key = key;
        this.name = name;
    }

    public BaseKeyValue(String code,String name){
        this.code = code;
        this.name = name;
    }

    public BaseKeyValue(Long key,String code,String name){
        this.key = key;
        this.code = code;
        this.name = name;
    }
}