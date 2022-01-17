package com.platform.cloud.common.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2022/1/17 0017
 * 创建人:pmc
 * 描述:基础分页参数
 */
@Data
public class BaseParamPage extends PTPageAdapt{
    /** 查询关键字 */
    @ApiModelProperty(value = "关键值", required = true)
    private String key;
}