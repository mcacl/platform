package com.platform.cloud.common.data.utils;

import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建时间:2021/10/8 0008
 * 创建人:pmc
 * 描述:数据转换工具类
 */
public class UtilConvert{
    /**
     * list类型转换
     * @param dataList 源list
     * @param target 目标class
     * @param <T> 源pojo
     * @param <R> 目标pojo
     * @return
     */
    public static <T,R> List<R> ConvertTtoR(List<T> dataList,Class<R> target){
        List<R> rList = Collections.emptyList();
        if(dataList != null && dataList.size() > 0){
            rList = dataList.stream().map(x->{
                R r = null;
                try{
                    r = target.newInstance();
                    BeanUtils.copyProperties(x,r);
                    return r;
                } catch(Exception e){
                    e.printStackTrace();
                }
                return r;
            }).collect(Collectors.toList());
        }
        return rList;
    }
}
