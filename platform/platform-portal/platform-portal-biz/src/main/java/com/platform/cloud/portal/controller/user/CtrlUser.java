package com.platform.cloud.portal.controller.user;

import com.platform.cloud.common.core.aspect.notes.Notes;
import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.user.client.ClientUser;
import com.platform.cloud.user.dto.DtoPlatformUser;
import com.platform.cloud.user.param.ParamUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间:2021/9/9 0009
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("users")
@Api(tags = {"测试类"})
public class CtrlUser{
    @Autowired
    private ClientUser clientUser;

    @Notes
    @ApiOperation("用户列表")
    @PostMapping("pagePlatformUser")
    public PTResponse<DtoPlatformUser> pagePlatformUser(@RequestBody ParamUser param){
        return clientUser.getPlatformUser(param);
    }

}