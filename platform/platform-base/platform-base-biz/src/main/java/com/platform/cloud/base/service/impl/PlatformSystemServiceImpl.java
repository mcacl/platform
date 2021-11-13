package com.platform.cloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.base.mapper.PlatformSystemMapper;
import com.platform.cloud.base.model.PlatformSystem;
import com.platform.cloud.base.service.PlatformSystemService;
import org.springframework.stereotype.Service;

@Service
public class PlatformSystemServiceImpl extends ServiceImpl<PlatformSystemMapper,PlatformSystem> implements PlatformSystemService{

}