package com.platform.cloud.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.platform.cloud.common.core.aspect.notes.Notes;
import com.platform.cloud.common.core.entity.PResponse;
import com.platform.cloud.common.core.entity.systeam.PlatformUser;
import com.platform.cloud.common.data.PTPage;
import com.platform.cloud.user.param.ParamUser;
import com.platform.cloud.user.service.PlatformUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("test")
@Api(tags = {"测试类"})
public class CtroleTest{
    @Autowired
    private PlatformUserService userService;

    @ApiOperation("测试接口")
    @PostMapping("pagePlatformUser")
    @Notes("用户列表 pmc")
    public PResponse<PTPage<PlatformUser>> pagePlatformUser(@RequestBody ParamUser param){
        var res = userService.page(param.buildPage(),new LambdaQueryWrapper<PlatformUser>().like(StringUtils.isNotEmpty(param.getName()),PlatformUser::getName,param.getName()));
        return PResponse.data(PTPage.BuildPTPage(res));
    }

}