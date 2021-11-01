package com.platform.cloud.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.user.mapper.PlatformUserMapper;
import com.platform.cloud.user.model.PlatformUser;
import com.platform.cloud.user.service.PlatformUserService;
import org.springframework.stereotype.Service;

@Service
public class PlatformUserServiceImpl extends ServiceImpl<PlatformUserMapper,PlatformUser> implements PlatformUserService{

}