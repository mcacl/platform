package com.platform.cloud.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.base.dto.DtoSysDictionary;
import com.platform.cloud.base.mapper.SysDictionaryMapper;
import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.param.ParamSysDictionary;
import com.platform.cloud.base.service.SysDictionaryService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper,SysDictionary> implements SysDictionaryService{
    @Override
    public DtoSysDictionary queryDictionaryTree(ParamQueryDic param){
        DtoSysDictionary dto = baseMapper.queryDicCode(param.getCode());
        if(param.isIncludeChildren()){
            List<DtoSysDictionary> list = baseMapper.queryDictionaryTree(dto.getId());
            queryDic(dto,list);//查子节点
        }
        return dto;
    }

    @Override
    public Boolean doDictionary(ParamSysDictionary param){
        boolean res = false;
        SysDictionary mode = new SysDictionary();
        BeanUtils.copyProperties(param,mode);
        if(ObjectUtils.isEmpty(mode.getId())){
            res = save(mode);
        }else{
            res = updateById(mode);
        }
        return res;
    }

    private void queryDic(DtoSysDictionary pdto,List<DtoSysDictionary> list){
        ArrayList<DtoSysDictionary> cList = new ArrayList<>();
        Iterator<DtoSysDictionary> iterator = list.iterator();
        while(iterator.hasNext()){
            DtoSysDictionary cdto = iterator.next();
            if(cdto.getPId().equals(pdto.getId())){
                cList.add(cdto);
                iterator.remove();
                queryDic(cdto,list);
            }
        }
        pdto.setChildren(cList);
    }
}