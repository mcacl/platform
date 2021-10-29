package com.platform.cloud.manager.entity;

import com.platform.cloud.common.core.entity.base.EntityBaseAutoId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建时间:2021/9/9 0009
 * 创建人:pmc
 * 描述:
 */
@Data
@NoArgsConstructor
public class EntityTest extends EntityBaseAutoId{
    @ApiModelProperty("名字")
    private String name;

    public EntityTest(Long id,String name){
        this.setId(id);
        this.name = name;
    }
}