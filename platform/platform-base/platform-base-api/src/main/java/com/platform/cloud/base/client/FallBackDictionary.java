package com.platform.cloud.base.client;

import com.platform.cloud.base.dto.DtoSysDictionary;
import com.platform.cloud.base.dto.DtoSysRegion;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.param.ParamQueryRegion;
import com.platform.cloud.base.param.ParamSysDictionary;
import com.platform.cloud.common.core.entity.PTResponse;

import java.util.List;

/**
 * 创建时间:2021/11/13
 * 创建人:pmc
 * 描述:
 */
public class FallBackDictionary implements ClientDictionary{
    private Throwable throwable;

    public FallBackDictionary(Throwable throwable){
        this.throwable = throwable;
    }

    @Override
    public PTResponse<Boolean> addDictionary(ParamSysDictionary param){
        return PTResponse.fallBackfailed();
    }

    @Override
    public PTResponse<List<DtoSysDictionary>> queryDictionaryTree(ParamQueryDic param){
        return PTResponse.fallBackfailed();
    }

    @Override
    public PTResponse<List<DtoSysRegion>> queryRegion(ParamQueryRegion param){
        return PTResponse.fallBackfailed();
    }
}