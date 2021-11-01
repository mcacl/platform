package com.platform.cloud.user.model;

import com.platform.cloud.common.core.entity.base.EntityBaseSnowflakeId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统用户表
 * @TableName platform_user
 */
@Data
public class PlatformUser extends EntityBaseSnowflakeId implements Serializable{

    /** 名称 */
    @NotBlank(message = "[名称]不能为空")
    @ApiModelProperty(value = "名称", required = true)
    private String name;

    /** 登录名 */
    @NotBlank(message = "[登录名]不能为空")
    @ApiModelProperty(value = "登录名", required = true)
    private String loginName;

    /** 密码 */
    @NotBlank(message = "[密码]不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    /** 账户可用1可用0不可用 */
    @NotNull(message = "[账户可用1可用0不可用]不能为空")
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
    @NotBlank(message = "[创建人id]不能为空")
    @ApiModelProperty(value = "创建人id", required = true)
    private String createUserId;

    /** 创建人 */
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /** 密码密钥 */
    @NotBlank(message = "[密码密钥]不能为空")
    @ApiModelProperty(value = "密码密钥", required = true)
    private String secretKey;

    /** 身份证件 */
    @ApiModelProperty(value = "身份证件")
    private String idCard;

    /**
     * id 前缀
     * @return
     */
    @Override
    public String idPrefix(){
        return "USR";
    }
}