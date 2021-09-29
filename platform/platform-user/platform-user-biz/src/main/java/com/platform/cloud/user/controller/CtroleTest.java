package com.platform.cloud.user.controller;

import com.platform.cloud.common.core.entity.PResponse;
import com.platform.cloud.user.entity.PlatformUser;
import com.platform.cloud.user.service.PlatformUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建时间:2021/9/9 0009
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("test")
@Api(tags = {"测试类"})
public class CtroleTest{
    @Autowired
    private PlatformUserService userService;

    @ApiOperation("测试接口")
    @PostMapping("listPlatformUser")
    public PResponse<List<PlatformUser>> listPlatformUser(){
        var res = userService.list();
        return PResponse.data(res);
    }

}
