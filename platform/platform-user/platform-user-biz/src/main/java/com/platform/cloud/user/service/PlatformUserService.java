package com.platform.cloud.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.cloud.user.model.PlatformUser;
import com.platform.cloud.user.param.ParamPageUser;
import com.platform.cloud.user.param.ParamUser;


public interface PlatformUserService extends IService<PlatformUser>{

    Page<PlatformUser> pagePlatformUser(ParamPageUser param);

    PlatformUser getPlatformUser(ParamUser param);
}