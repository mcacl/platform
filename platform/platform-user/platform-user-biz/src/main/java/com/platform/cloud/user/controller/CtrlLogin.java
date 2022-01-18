package com.platform.cloud.user.controller;

import com.platform.cloud.common.core.aspect.notes.Notes;
import com.platform.cloud.common.core.entity.PTResponse;
import com.platform.cloud.user.dto.DtoLoginResponse;
import com.platform.cloud.user.param.ParamLoginAccount;
import com.platform.cloud.user.service.login.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间:2022/1/18 0018
 * 创建人:pmc
 * 描述:
 */
@Api(tags = {"登录"})
@RestController
@RequestMapping("login")
public class CtrlLogin{

    private LoginService loginService;

    public CtrlLogin(LoginService loginService){
        this.loginService = loginService;
    }

    @Notes
    @ApiModelProperty("账户系统登录")
    @PostMapping()
    public PTResponse<DtoLoginResponse> loginAccount(@RequestBody @Validated ParamLoginAccount param){
        DtoLoginResponse response = loginService.loginAccount(param);
        return PTResponse.data(response);
    }
}