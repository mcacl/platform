package com.platform.cloud.user.dto;

import com.platform.cloud.common.core.entity.base.dto.BaseDtoNumberId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建时间:2021/11/2 0002
 * 创建人:pmc
 * 描述:
 */
@Data
public class DtoNumberPlatformUser extends BaseDtoNumberId{
    /** 名称 */
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /** 登录名 */
    @ApiModelProperty(value = "登录名", required = true)
    private String loginName;

    /** 账户可用1可用0不可用 */
    @ApiModelProperty(value = "账户可用1可用0不可用", required = true)
    private Integer state;

    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /** 性别 */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /** 上次登录时间 */
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime loginTime;

    /** 创建人id */
    @ApiModelProperty(value = "创建人id", required = true)
    private String createUserId;

    /** 创建人 */
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /** 密码密钥 */
    @ApiModelProperty(value = "密码密钥", required = true)
    private String secretKey;

    /** 身份证件 */
    @ApiModelProperty(value = "身份证件")
    private String idCard;
}