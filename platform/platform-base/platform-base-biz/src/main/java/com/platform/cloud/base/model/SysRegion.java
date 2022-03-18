package com.platform.cloud.base.model;

import com.platform.cloud.common.core.entity.base.BaseDelEntityAutoId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 区域字典表
 * @TableName sys_region
 */
@Data
public class SysRegion extends BaseDelEntityAutoId implements Serializable{

    /** 区域编码 */
    @NotBlank(message = "[区域编码]不能为空")
    @ApiModelProperty(value = "区域编码", required = true)
    private String regionCode;

    /** 区域名称 */
    @NotBlank(message = "[区域名称]不能为空")
    @ApiModelProperty(value = "区域名称", required = true)
    private String regionName;

    /** 区域上级标识 */
    @NotBlank(message = "[区域上级标识]不能为空")
    @ApiModelProperty(value = "区域上级标识", required = true)
    private String parentCode;

    /** 地名简称 */
    @NotBlank(message = "[地名简称]不能为空")
    @ApiModelProperty(value = "地名简称", required = true)
    private String simpleName;

    /** 区域等级 */
    @NotNull(message = "[区域等级]不能为空")
    @ApiModelProperty(value = "区域等级", required = true)
    private Integer regionLevel;

    /** 城市编码 */
    @NotBlank(message = "[城市编码]不能为空")
    @ApiModelProperty(value = "城市编码", required = true)
    private String cityCode;

    /** 邮政编码 */
    @NotBlank(message = "[邮政编码]不能为空")
    @ApiModelProperty(value = "邮政编码", required = true)
    private String zipCode;

    /** 组合名称 */
    @NotBlank(message = "[组合名称]不能为空")
    @ApiModelProperty(value = "组合名称", required = true)
    private String merName;

    /** 经度 */
    @NotNull(message = "[经度]不能为空")
    @ApiModelProperty(value = "经度", required = true)
    private Double lgn;

    /** 纬度 */
    @NotNull(message = "[纬度]不能为空")
    @ApiModelProperty(value = "纬度", required = true)
    private Double lat;

    /** 拼音 */
    @NotBlank(message = "[拼音]不能为空")
    @ApiModelProperty(value = "拼音", required = true)
    private String pinYin;
}