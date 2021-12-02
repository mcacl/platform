package com.platform.cloud.common.core.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:自增id基类物理删除实体
 */
@Data
public abstract class BaseDelEntityAutoId extends BaseEntity{
    /**
     * 数据库自增id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;
}