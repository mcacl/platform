package com.platform.cloud.user.client;

import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.common.core.enums.EnumApplications;
import com.platform.cloud.common.data.dto.PTPage;
import com.platform.cloud.user.dto.DtoPlatformUser;
import com.platform.cloud.user.param.ParamPageUser;
import com.platform.cloud.user.param.ParamUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 创建时间:2022/1/17 0017
 * 创建人:pmc
 * 描述:
 */
@FeignClient(contextId = "ClientUser", value = EnumApplications.NAME_USER, fallbackFactory = FactoryUser.class)
public interface ClientUser{
    final String ROOTURL = EnumApplications.NAME_USER + "/";

    //"用户分页列表"
    @PostMapping(ROOTURL + "users/pagePlatformUser")
    public PTResponse<PTPage<DtoPlatformUser>> pagePlatformUser(@RequestBody ParamPageUser param);

    //("单个用户")
    @PostMapping(ROOTURL + "users/getPlatformUser")
    public PTResponse<DtoPlatformUser> getPlatformUser(@RequestBody ParamUser param);
}