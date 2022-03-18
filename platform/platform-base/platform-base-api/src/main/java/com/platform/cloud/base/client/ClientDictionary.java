package com.platform.cloud.base.client;

import com.platform.cloud.base.dto.DtoSysDictionary;
import com.platform.cloud.base.dto.DtoSysRegion;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.param.ParamQueryRegion;
import com.platform.cloud.base.param.ParamSysDictionary;
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
    String basePath = EnumApplications.NAME_BASE + "/dictionary/";

    //@ApiOperation("字典新增、修改")
    @PostMapping(basePath + "addDictionary")
    public PTResponse<Boolean> addDictionary(@RequestBody @Validated ParamSysDictionary param);

    //@ApiOperation("字典查询")
    @PostMapping(basePath + "queryDictionaryTree")
    public PTResponse<List<DtoSysDictionary>> queryDictionaryTree(@RequestBody @Validated ParamQueryDic param);

    //@ApiOperation("地区查询")
    @PostMapping(basePath + "queryRegion")
    public PTResponse<List<DtoSysRegion>> queryRegion(@RequestBody ParamQueryRegion param);
}