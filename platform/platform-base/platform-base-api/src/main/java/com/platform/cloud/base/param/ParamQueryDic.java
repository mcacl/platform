package com.platform.cloud.base.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:
 */
@Data
public class ParamQueryDic{
    @NotNull
    @ApiModelProperty(value = "id")
    private Long code;
    @ApiModelProperty(value = "是否查询子节点", required = true)
    private boolean includeChildren = false;
}