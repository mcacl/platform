package com.platform.cloud.user.service.login.impl;

import com.platform.cloud.user.dto.DtoLoginResponse;
import com.platform.cloud.user.model.PlatformUser;
import com.platform.cloud.user.param.ParamLoginAccount;
import com.platform.cloud.user.param.ParamUser;
import com.platform.cloud.user.service.PlatformUserService;
import com.platform.cloud.user.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建时间:2022/1/18 0018
 * 创建人:pmc
 * 描述:
 */
@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    private PlatformUserService userService;

    @Override
    public DtoLoginResponse loginAccount(ParamLoginAccount param){
        ParamUser paramUser = new ParamUser(param.getAct());
        PlatformUser user = userService.getPlatformUser(paramUser);
        DtoLoginResponse response = new DtoLoginResponse();
        return response;
    }
}