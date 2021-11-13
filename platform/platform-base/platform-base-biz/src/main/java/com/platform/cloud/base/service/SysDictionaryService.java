package com.platform.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.param.ParamQueryDict;

import java.util.List;

public interface SysDictionaryService extends IService<SysDictionary>{

    List<SysDictionary> queryDictionary(ParamQueryDict param);
}