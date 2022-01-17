package com.platform.cloud.base.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.base.mapper.PlatformSystemMapper;
import com.platform.cloud.base.model.PlatformSystem;
import com.platform.cloud.base.service.PlatformSystemService;
import com.platform.cloud.common.core.entity.base.param.BaseParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformSystemServiceImpl extends ServiceImpl<PlatformSystemMapper,PlatformSystem> implements PlatformSystemService{

    @Override
    public List<PlatformSystem> listPlatformSystem(){
        return list();
    }

    @Override
    public Boolean doPlatformSystem(PlatformSystem param){
        boolean res;
        if(ObjectUtil.isNotNull(param.getId())){
            res = save(param);
        }else{
            res = updateById(param);
        }
        return res;
    }

    @Override
    public Boolean delPlatformSystem(BaseParam param){
        return removeById(param.getKey());
    }
}