package com.platform.cloud.user.param;

import com.platform.cloud.common.data.dto.PTPageAdapt;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2022/1/17 0017
 * 创建人:pmc
 * 描述:
 */
@Data
public class ParamPageUser extends PTPageAdapt{
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /** 性别 */
    @ApiModelProperty(value = "性别")
    private Integer sex;

    /** 电话 */
    @ApiModelProperty(value = "电话")
    private String phone;
}