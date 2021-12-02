package com.platform.cloud.common.core.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:基类实体
 */
@Data
public abstract class BaseEntity{
    /**
     * 数据创建时间
     */
    @ApiModelProperty("数据创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 数据更新时间
     */
    @ApiModelProperty("数据更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}