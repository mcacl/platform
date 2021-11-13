package com.platform.cloud.common.core.entity.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:基础查询参数
 */
@Data
public class BaseParam{
    @NotBlank(message = "关键值不能空")
    @ApiModelProperty("关键值")
    private String key;
}