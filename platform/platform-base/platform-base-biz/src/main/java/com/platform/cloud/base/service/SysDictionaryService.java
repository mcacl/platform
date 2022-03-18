package com.platform.cloud.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.cloud.base.dto.DtoSysDictionary;
import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.param.ParamSysDictionary;

public interface SysDictionaryService extends IService<SysDictionary>{

    DtoSysDictionary queryDictionaryTree(ParamQueryDic param);

    Boolean doDictionary(ParamSysDictionary param);
}