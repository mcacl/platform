package com.platform.cloud.user.service.login;

import com.platform.cloud.user.dto.DtoLoginResponse;
import com.platform.cloud.user.param.ParamLoginAccount;
import org.springframework.stereotype.Service;

/**
 * 创建时间:2022/1/18 0018
 * 创建人:pmc
 * 描述:
 */
@Service
public interface LoginService{

    DtoLoginResponse loginAccount(ParamLoginAccount param);
}