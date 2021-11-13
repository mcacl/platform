package com.platform.cloud.base.client;

import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.model.SysRegion;
import com.platform.cloud.base.param.ParamQueryDict;
import com.platform.cloud.base.param.ParamQueryRegion;
import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.common.core.enums.EnumApplications;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:
 */
@FeignClient(contextId = "ClientDictionary", value = EnumApplications.NAME_BASE, fallback = FactoryDictionary.class)
public interface ClientDictionary{
    String basePath = EnumApplications.NAME_BASE + "/dictionary";

    //@ApiOperation("字典新增、修改")
    @PostMapping(basePath + "/addDictionary")
    public PTResponse<Boolean> addDictionary(@RequestBody @Validated SysDictionary param);

    //@ApiOperation("字典查询")
    @PostMapping("queryDictionary")
    public PTResponse<List<SysDictionary>> queryDictionary(@RequestBody @Validated ParamQueryDict param);

    //@ApiOperation("地区查询")
    @PostMapping("queryRegion")
    public PTResponse<List<SysRegion>> queryRegion(@RequestBody ParamQueryRegion param);
}