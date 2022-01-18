package com.platform.cloud.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2022/1/18 0018
 * 创建人:pmc
 * 描述:
 */
@Data
public class DtoLoginResponse{
    /** 用户id */
    @ApiModelProperty("用户id")
    private String mid;
    /** token */
    @ApiModelProperty("token")
    private String k;

}