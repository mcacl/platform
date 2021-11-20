package com.platform.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.param.ParamQueryDic;

import java.util.List;

public interface SysDictionaryService extends IService<SysDictionary>{

    List<SysDictionary> queryDictionary(ParamQueryDic param);

    Boolean doDictionary(SysDictionary param);
}