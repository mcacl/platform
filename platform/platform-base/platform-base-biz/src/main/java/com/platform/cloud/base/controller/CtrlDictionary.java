package com.platform.cloud.base.controller;

import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.model.SysRegion;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.param.ParamQueryRegion;
import com.platform.cloud.base.service.SysDictionaryService;
import com.platform.cloud.base.service.SysRegionService;
import com.platform.cloud.common.core.aspect.notes.Notes;
import com.platform.cloud.common.core.entity.PTResponse;
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
 * 创建时间:2021/9/9 0009
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("dictionary")
@Api(tags = {"字典管理服务"})
public class CtrlDictionary{
    @Autowired
    private SysDictionaryService dictionaryService;
    @Autowired
    private SysRegionService regionService;

    @Notes
    @ApiOperation("字典新增、修改")
    @PostMapping("doDictionary")
    public PTResponse<Boolean> doDictionary(@RequestBody @Validated SysDictionary param){
        return PTResponse.data(dictionaryService.doDictionary(param));
    }

    @Notes
    @ApiOperation("字典查询")
    @PostMapping("queryDictionary")
    public PTResponse<List<SysDictionary>> queryDictionary(@RequestBody @Validated ParamQueryDic param){
        return PTResponse.data(dictionaryService.queryDictionary(param));
    }

    @Notes
    @ApiOperation("地区查询")
    @PostMapping("queryRegion")
    public PTResponse<List<SysRegion>> queryRegion(@RequestBody ParamQueryRegion param){
        return PTResponse.data(regionService.queryRegion(param));
    }
}