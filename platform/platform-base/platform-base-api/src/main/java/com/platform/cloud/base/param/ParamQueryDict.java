package com.platform.cloud.base.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:
 */
@Data
public class ParamQueryDict{
    @ApiModelProperty(value = "父级id")
    private Set<Long> pids;
    @ApiModelProperty(value = "是否查询子节点", required = true)
    private boolean includeChildren = true;
}