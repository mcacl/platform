package com.platform.cloud.common.data;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.platform.cloud.common.data.constant.ConstantCom.*;

/**
 * 创建时间:2021/10/8 0008
 * 创建人:pmc
 * 描述:分页前端基础参数
 */
@Data
public class PTPageAdapt{

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码[默认:1,起始页码:1]", example = "1")
    private Integer pageCurrent = DEFAULT_PAGE_CURRENT;

    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数[默认:15]", example = "15")
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 排序规则
     */
    @ApiModelProperty(value = "排序规则", notes = "见示例[名称倒序]", example = "[{\"column\":name,\"asc\":false}]")
    private List<OrderItem> orderList;

    public PTPageAdapt(){
        this.orderList = new ArrayList<>();
    }

    public <T> Page<T> buildPage(){
        Page<T> page = new Page<>();
        page.setSize(Optional.ofNullable(this.pageSize).orElse(DEFAULT_PAGE_SIZE));
        page.setCurrent(Optional.ofNullable(this.pageCurrent).orElse(DEFAULT_PAGE_CURRENT));
        if(!orderList.isEmpty()){
            page.setOrders(orderList);
        }
        return page;
    }

    public void setPageCurrent(Integer pageCurrent){
        if(Objects.isNull(pageCurrent) || pageCurrent < 1){
            this.pageCurrent = 1;
        }else{
            this.pageCurrent = pageCurrent;
        }
    }

    public void setPageSize(Integer pageSize) throws Exception{
        if(Objects.isNull(pageSize) || pageSize < 1){
            this.pageSize = DEFAULT_PAGE_SIZE;
        }else{
            this.pageSize = pageSize;
        }
        if(this.pageSize > DEFAULT_PAGE_MAX_SIZE){
            throw new Exception(String.format("pageSize过大[1<=%s<=%s]",pageSize,DEFAULT_PAGE_MAX_SIZE));
        }
    }
}
