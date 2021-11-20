package com.platform.cloud.base.client;

import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.model.SysRegion;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.param.ParamQueryRegion;
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
    public PTResponse<Boolean> addDictionary(SysDictionary param){
        return PTResponse.fallBackfailed();
    }

    @Override
    public PTResponse<List<SysDictionary>> queryDictionary(ParamQueryDic param){
        return PTResponse.fallBackfailed();
    }

    @Override
    public PTResponse<List<SysRegion>> queryRegion(ParamQueryRegion param){
        return PTResponse.fallBackfailed();
    }
}