package com.platform.cloud.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.cloud.common.core.aspect.notes.Notes;
import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.common.data.dto.PTPage;
import com.platform.cloud.user.dto.DtoPlatformUser;
import com.platform.cloud.user.model.PlatformUser;
import com.platform.cloud.user.param.ParamPageUser;
import com.platform.cloud.user.param.ParamUser;
import com.platform.cloud.user.service.PlatformUserService;
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
@Api(tags = {"用户"})
public class CtrlUser{
    @Autowired
    private PlatformUserService userService;

    @Notes
    @ApiOperation("用户列表")
    @PostMapping("pagePlatformUser")
    public PTResponse<PTPage<DtoPlatformUser>> pagePlatformUser(@RequestBody ParamPageUser param){
        Page<PlatformUser> res = userService.pagePlatformUser(param);
        return PTResponse.data(PTPage.BuildPTPage(res,DtoPlatformUser.class));
    }

    @Notes
    @ApiOperation("单个用户")
    @PostMapping("getPlatformUser")
    public PTResponse<PlatformUser> getPlatformUser(@RequestBody ParamUser param){
        PlatformUser res = userService.getPlatformUser(param);
        return PTResponse.data(res);
    }
}