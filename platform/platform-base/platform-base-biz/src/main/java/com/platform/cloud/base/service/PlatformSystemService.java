package com.platform.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.cloud.base.model.PlatformSystem;
import com.platform.cloud.common.core.entity.base.param.BaseParam;

import java.util.List;

public interface PlatformSystemService extends IService<PlatformSystem>{

    List<PlatformSystem> listPlatformSystem();

    Boolean doPlatformSystem(PlatformSystem param);

    Boolean delPlatformSystem(BaseParam param);
}