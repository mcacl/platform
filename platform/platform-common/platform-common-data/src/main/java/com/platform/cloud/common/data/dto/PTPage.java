package com.platform.cloud.common.data.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.platform.cloud.common.data.utils.UtilConvert;
import lombok.Data;
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
@Data
public class PTPage<T>{
    private Long current, size, total;
    /**
     * 分页数据
     */
    private List<T> records;
    /**
     * 排序列
     */
    private List<OrderItem> orders;

    public PTPage(){
        this(DEFAULT_PAGE_CURRENT,DEFAULT_PAGE_SIZE,DEFAULT_PAGE_TOTAL);
    }

    public PTPage(Page<T> page){
        this(page.getCurrent(),page.getSize(),page.getTotal());
        this.setRecords(page.getRecords());
        this.setOrders(page.orders());
    }

    public PTPage(long current,long size,long total){
        this.current = current;
        this.size = size;
        this.total = total;
        this.records = Collections.emptyList();
        this.orders = Collections.emptyList();
    }

    public static <T> PTPage<T> BuildPTPage(Page<T> page){
        PTPage ptPage = new PTPage(page);
        return ptPage;
    }

    public static <T,R> PTPage<R> BuildPTPage(Page<T> page,Class<R> targetClass){
        List<R> list = Collections.emptyList();
        PTPage ptPage = new PTPage(page);
        if(page.getRecords().size() > 0){
            list = UtilConvert.ConvertTtoR(page.getRecords(),targetClass);
        }
        ptPage.setRecords(list);
        return ptPage;
    }
}