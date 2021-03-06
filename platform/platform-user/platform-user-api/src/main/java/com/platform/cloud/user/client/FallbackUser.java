package com.platform.cloud.user.client;

import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.common.data.dto.PTPage;
import com.platform.cloud.user.dto.DtoPlatformUser;
import com.platform.cloud.user.param.ParamPageUser;
import com.platform.cloud.user.param.ParamUser;

/**
 * 创建时间:2022/1/17 0017
 * 创建人:pmc
 * 描述:
 */
public class FallbackUser implements ClientUser{
    private final Throwable throwable;

    public FallbackUser(Throwable throwable){
        this.throwable = throwable;
    }

    @Override
    public PTResponse<PTPage<DtoPlatformUser>> pagePlatformUser(ParamPageUser param){
        return PTResponse.fallBackfailed();
    }

    @Override
    public PTResponse<DtoPlatformUser> getPlatformUser(ParamUser param){
        return PTResponse.fallBackfailed();
    }
}