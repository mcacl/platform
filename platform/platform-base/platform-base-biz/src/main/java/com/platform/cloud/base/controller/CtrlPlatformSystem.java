package com.platform.cloud.base.controller;

import com.platform.cloud.base.model.PlatformSystem;
import com.platform.cloud.base.service.PlatformSystemService;
import com.platform.cloud.common.core.aspect.notes.Notes;
import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.common.core.entity.base.BaseParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建时间:2021/11/20
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("platformSystem")
@Api(tags = {"平台系统信息服务"})
public class CtrlPlatformSystem{
    @Autowired
    private PlatformSystemService systemService;

    @Notes
    @ApiOperation("平台全部系统信息")
    @PostMapping("listPlatformSystem")
    public PTResponse<List<PlatformSystem>> listPlatformSystem(){
        return PTResponse.data(systemService.listPlatformSystem());
    }

    @Notes
    @ApiOperation("平台系统信息增改")
    @PostMapping("doPlatformSystem")
    public PTResponse<Boolean> doPlatformSystem(@RequestBody @Validated PlatformSystem param){
        return PTResponse.data(systemService.doPlatformSystem(param));
    }

    @Notes
    @ApiOperation("平台系统信息删除")
    @PostMapping("delPlatformSystem")
    public PTResponse<Boolean> delPlatformSystem(@RequestBody @Validated BaseParam param){
        return PTResponse.data(systemService.delPlatformSystem(param));
    }
}