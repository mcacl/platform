package com.platform.cloud.common.core.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:自增id基类实体
 */
@Data
public abstract class EntityBaseAutoId extends EntityBase{
    /**
     * 数据库自增id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "[id]不能为空")
    private Long id;
}