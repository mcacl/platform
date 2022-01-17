package com.platform.cloud.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/10/9 0009
 * 创建人:pmc
 * 描述:
 */
@Data
public class ParamUser{
    private String id;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    private String phone;
    /** 身份证 */
    @ApiModelProperty(value = "身份证")
    private String idCard;

}