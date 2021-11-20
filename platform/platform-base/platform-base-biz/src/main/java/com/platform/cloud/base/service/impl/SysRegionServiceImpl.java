package com.platform.cloud.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.base.mapper.SysRegionMapper;
import com.platform.cloud.base.model.SysRegion;
import com.platform.cloud.base.param.ParamQueryRegion;
import com.platform.cloud.base.service.SysRegionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRegionServiceImpl extends ServiceImpl<SysRegionMapper,SysRegion> implements SysRegionService{

    @Override
    public List<SysRegion> queryRegion(ParamQueryRegion param){
        List<SysRegion> res = list(new LambdaQueryWrapper<SysRegion>().eq(SysRegion::getParentCode,param.getParentCode()));
        return res;
    }
}