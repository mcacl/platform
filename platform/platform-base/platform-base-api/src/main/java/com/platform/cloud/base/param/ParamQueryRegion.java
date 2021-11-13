package com.platform.cloud.base.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:查地区参数
 */
@Data
public class ParamQueryRegion{
    @ApiModelProperty(value = "父级地区码--全国100000")
    private String parentCode = "100000";
}