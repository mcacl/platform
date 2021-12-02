package com.platform.cloud.common.core.entity.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/12/2 0002
 * 创建人:pmc
 * 描述:含逻辑删除的基类实体
 */
@Data
public class BaseLogicDelEntity extends BaseEntity{
    /**
     * 逻辑删除
     */
    @JsonIgnore
    @TableLogic
    @ApiModelProperty(value = "逻辑删除", hidden = true)
    private Integer isDel;
}