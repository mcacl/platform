package com.platform.cloud.common.data;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.cloud.common.data.utils.UtilConvert;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

import static com.platform.cloud.common.data.constant.ConstantCom.*;

/**
 * 创建时间:2021/10/8 0008
 * 创建人:pmc
 * 描述:
 */
@Slf4j
public class PTPage<T> extends Page<T>{
    public PTPage(){
        super(DEFAULT_PAGE_CURRENT,DEFAULT_PAGE_SIZE,DEFAULT_PAGE_TOTAL);
    }

    public PTPage(int current,int size,int total){
        super(current,size,total);
    }

    public static <T,R> PTPage<R> BuildPTPage(Page<T> page,Class<R> targetClass){
        List<R> list = Collections.emptyList();
        PTPage ptPage = new PTPage();
        if(page.getRecords().size() > 0){
            list = UtilConvert.ConvertTtoR(page.getRecords(),targetClass);
        }
        ptPage.setRecords(list);
        if(page.getCurrent() > 1L){
            ptPage.setCurrent(page.getCurrent());
        }
        ptPage.setSize(page.getSize());
        ptPage.setTotal(page.getTotal());
        return ptPage;
    }
}
