package com.platform.cloud.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.cloud.base.dto.DtoSysDictionary;
import com.platform.cloud.base.model.SysDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.platform.cloud.base.model.SysDictionary
 */
public interface SysDictionaryMapper extends BaseMapper<SysDictionary>{

    List<DtoSysDictionary> queryDictionaryTree(@Param("param") Long id);

    DtoSysDictionary queryDicCode(@Param("code") Long code);
}