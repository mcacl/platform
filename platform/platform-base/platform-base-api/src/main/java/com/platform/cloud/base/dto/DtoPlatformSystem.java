package com.platform.cloud.base.dto;

import com.platform.cloud.common.core.entity.base.BaseEntityAutoId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 平台系统
 * @TableName platform_system
 */
@Data
public class DtoPlatformSystem extends BaseEntityAutoId implements Serializable{

    /** 系统名称 */
    @NotBlank(message = "[系统名称]不能为空")
    @ApiModelProperty(value = "系统名称", required = true)
    private String name;

    /** 系统介绍 */
    @ApiModelProperty(value = "系统介绍")
    private String description;

    /** 系统地址 */
    @ApiModelProperty(value = "系统地址")
    private String url;
}