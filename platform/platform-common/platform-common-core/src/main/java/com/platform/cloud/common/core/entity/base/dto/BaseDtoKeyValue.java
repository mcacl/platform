package com.platform.cloud.common.core.entity.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/12/2 0002
 * 创建人:pmc
 * 描述:基础键值实体
 */
@Data
public class BaseDtoKeyValue{
    @ApiModelProperty(value = "键Long")
    private Long key;
    @ApiModelProperty("键String")
    private String code;
    @ApiModelProperty("值")
    private String name;

    public BaseDtoKeyValue(){
    }

    public BaseDtoKeyValue(Long key,String name){
        this.key = key;
        this.name = name;
    }

    public BaseDtoKeyValue(String code,String name){
        this.code = code;
        this.name = name;
    }

    public BaseDtoKeyValue(Long key,String code,String name){
        this.key = key;
        this.code = code;
        this.name = name;
    }
}