package com.platform.cloud.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.user.mapper.PlatformUserMapper;
import com.platform.cloud.user.model.PlatformUser;
import com.platform.cloud.user.param.ParamPageUser;
import com.platform.cloud.user.param.ParamUser;
import com.platform.cloud.user.service.PlatformUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PlatformUserServiceImpl extends ServiceImpl<PlatformUserMapper,PlatformUser> implements PlatformUserService{

    @Override
    public Page<PlatformUser> pagePlatformUser(ParamPageUser param){
        return baseMapper.selectPage(param.buildPage(),new LambdaQueryWrapper<PlatformUser>().like(StringUtils.isNotEmpty(param.getName()),PlatformUser::getName,param.getName()).like(StringUtils.isNotBlank(param.getNickName()),PlatformUser::getNickName,param.getNickName()).like(StringUtils.isNotBlank(param.getPhone()),PlatformUser::getPhone,param.getPhone()).eq(ObjectUtil.isNotNull(param.getSex()),PlatformUser::getSex,param.getSex()));
    }

    @Override
    public PlatformUser getPlatformUser(ParamUser param){
        return baseMapper.selectOne(new LambdaQueryWrapper<PlatformUser>().eq(StringUtils.isNotBlank(param.getId()),PlatformUser::getId,param.getId()).eq(StringUtils.isNotBlank(param.getIdCard()),PlatformUser::getIdCard,param.getIdCard()).eq(StringUtils.isNotBlank(param.getPhone()),PlatformUser::getPhone,param.getPhone()));
    }
}