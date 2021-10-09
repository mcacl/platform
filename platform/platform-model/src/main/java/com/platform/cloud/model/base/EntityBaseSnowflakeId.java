package com.platform.cloud.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * 创建时间:2021/9/7 0007
 * 创建人:pmc
 * 描述:雪花uuid基类实体
 */
public abstract class EntityBaseSnowflakeId extends EntityBase{
    /**
     * 应用雪花算法的id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @ApiModelProperty("雪花id")
    private String id;

    /**
     * id 前缀
     * @return
     */
    @JsonIgnore
    public abstract String idPrefix();

    /**
     * 用于重写id
     * @return
     */
    @JsonIgnore
    public String idOverride(){
        return null;
    }
}
