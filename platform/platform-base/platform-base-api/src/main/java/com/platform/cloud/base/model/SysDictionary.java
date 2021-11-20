package com.platform.cloud.base.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.platform.cloud.common.core.entity.base.BaseEntityAutoId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 系统字典表
 * @TableName sys_dictionary
 */
@Data
public class SysDictionary extends BaseEntityAutoId implements Serializable{

    /** 父节点id */
    @ApiModelProperty(value = "父节点id")
    private Long pId;

    /** 字典码 */
    @NotNull(message = "[字典码]不能为空")
    @ApiModelProperty(value = "字典码", required = true)
    private Long code;

    /** 字典值 */
    @NotBlank(message = "[字典值]不能为空")
    @ApiModelProperty(value = "字典值", required = true)
    private String value;

    /** 说明 */
    @ApiModelProperty(value = "说明")
    private String description;

    /** 备注 */
    @ApiModelProperty(value = "备注")
    private String remark;

    /** 字典索引,全级id */
    @NotBlank(message = "[字典索引,全级id]不能为空")
    @ApiModelProperty(value = "字典索引,全级id", required = true)
    private String navigation;

    /** 排序 */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /** 所属系统 */
    @NotNull(message = "[所属系统]不能为空")
    @ApiModelProperty(value = "所属系统", required = true)
    private Long belongSystem;

    @TableField(exist = false)
    @ApiModelProperty("子节点 不传参数")
    private List<SysDictionary> children;
}