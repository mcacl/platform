package com.platform.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.cloud.base.model.SysRegion;
import com.platform.cloud.base.param.ParamQueryRegion;

import java.util.List;

public interface SysRegionService extends IService<SysRegion>{

    List<SysRegion> queryRegion(ParamQueryRegion param);
}