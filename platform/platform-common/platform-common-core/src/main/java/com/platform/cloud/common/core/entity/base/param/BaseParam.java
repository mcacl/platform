package com.platform.cloud.common.core.entity.base.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:基础查询参数
 */
@Data
public class BaseParam{
    /** 查询关键字 */
    @ApiModelProperty(value = "关键值", required = true)
    private String key;
}