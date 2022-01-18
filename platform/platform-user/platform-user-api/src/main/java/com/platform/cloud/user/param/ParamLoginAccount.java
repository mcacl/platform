package com.platform.cloud.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建时间:2022/1/18 0018
 * 创建人:pmc
 * 描述:
 */
@Data
public class ParamLoginAccount{
    /** 账户 */
    @NotBlank(message = "请输入账户")
    @ApiModelProperty("账户")
    private String act;
    /** 密码 */
    @NotBlank(message = "请输入密码")
    @ApiModelProperty("密码")
    private String pwd;
}