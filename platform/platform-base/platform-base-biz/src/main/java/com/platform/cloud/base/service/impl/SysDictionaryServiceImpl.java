package com.platform.cloud.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.cloud.base.mapper.SysDictionaryMapper;
import com.platform.cloud.base.model.SysDictionary;
import com.platform.cloud.base.param.ParamQueryDic;
import com.platform.cloud.base.service.SysDictionaryService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictionaryServiceImpl extends ServiceImpl<SysDictionaryMapper,SysDictionary> implements SysDictionaryService{
    @Override
    public List<SysDictionary> queryDictionary(ParamQueryDic param){
        List<SysDictionary> list = baseMapper.selectList(new LambdaQueryWrapper<SysDictionary>().in(SysDictionary::getPId,param.getPids()));
        if(param.isIncludeChildren()){
            queryDic(list);//查子节点
        }
        return list;
    }

    private void queryDic(List<SysDictionary> list){
        list.forEach(dic->{
            Long pid = dic.getId();
            if(pid != null){
                List<SysDictionary> childList = baseMapper.selectList(new LambdaQueryWrapper<SysDictionary>().eq(SysDictionary::getPId,pid));
                if(childList.size() > 0){
                    dic.setChildren(childList);
                    queryDic(childList);
                }
            }
        });
    }

    @Override
    public Boolean doDictionary(SysDictionary param){
        boolean res = false;
        if(ObjectUtils.isEmpty(param.getId())){
            res = save(param);
        }else{
            res = updateById(param);
        }
        return res;
    }
}