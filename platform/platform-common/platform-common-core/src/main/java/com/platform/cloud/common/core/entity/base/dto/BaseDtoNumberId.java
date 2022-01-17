package com.platform.cloud.common.core.entity.base.dto;

import com.platform.cloud.common.core.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2022/1/17 0017
 * 创建人:pmc
 * 描述:基础vo id实体
 */
@Data
public class BaseDtoNumberId extends BaseEntity{
    @ApiModelProperty("id")
    private Long id;
}