package com.platform.cloud.user.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName platform_user
 */
@Data
public class PlatformUser implements Serializable{

    /**
     * id
     */
    @NotNull(message = "[id]不能为空")
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 名称
     */
    @NotBlank(message = "[名称]不能为空")
    @Size(max = 50, message = "长度需小于50")
    @ApiModelProperty("名称")
    @Length(max = 50, message = "长度需小于50")
    private String name;
    /**
     * 登录名
     */
    @NotBlank(message = "[登录名]不能为空")
    @Size(max = 50, message = "长度需小于50")
    @ApiModelProperty("登录名")
    @Length(max = 50, message = "长度需小于50")
    private String loginName;
    /**
     * 密码
     */
    @NotBlank(message = "[密码]不能为空")
    @Size(max = 100, message = "长度需小于100")
    @ApiModelProperty("密码")
    @Length(max = 100, message = "长度需小于100")
    private String password;
    /**
     * 账户可用1可用0不可用
     */
    @NotNull(message = "[账户可用1可用0不可用]不能为空")
    @ApiModelProperty("账户可用1可用0不可用")
    private Integer state;
    /**
     * 昵称
     */
    @Size(max = 100, message = "长度需小于100")
    @ApiModelProperty("昵称")
    @Length(max = 100, message = "长度需小于100")
    private String nickName;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer sex;
    /**
     * 电话
     */
    @Size(max = 100, message = "长度需小于100")
    @ApiModelProperty("电话")
    @Length(max = 100, message = "长度需小于100")
    private String phone;
    /**
     * 邮箱
     */
    @Size(max = 100, message = "长度需小于100")
    @ApiModelProperty("邮箱")
    @Length(max = 100, message = "长度需小于100")
    private String email;
    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    private LocalDateTime loginTime;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
    /**
     * 创建人id
     */
    @NotNull(message = "[创建人id]不能为空")
    @ApiModelProperty("创建人id")
    private Integer createUserId;
    /**
     * 创建人
     */
    @Size(max = 50, message = "长度需小于50")
    @ApiModelProperty("创建人")
    @Length(max = 50, message = "长度需小于50")
    private String createUser;
    /**
     * 逻辑删除0否1是
     */
    @NotNull(message = "[逻辑删除0否1是]不能为空")
    @ApiModelProperty("逻辑删除0否1是")
    private Integer isDel;
    /**
     * 密码密钥
     */
    @NotBlank(message = "[密码密钥]不能为空")
    @Size(max = 100, message = "长度需小于100")
    @ApiModelProperty("密码密钥")
    @Length(max = 100, message = "长度需小于100")
    private String secretKey;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
    /**
     * 身份证件
     */
    @Size(max = 20, message = "长度需小于20")
    @ApiModelProperty("身份证件")
    @Length(max = 20, message = "长度需小于20")
    private String idCard;
}
