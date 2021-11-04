package com.platform.cloud.user.param;

import com.platform.cloud.common.data.PTPageAdapt;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 创建时间:2021/10/9 0009
 * 创建人:pmc
 * 描述:
 */
@Data
public class ParamUser extends PTPageAdapt{
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