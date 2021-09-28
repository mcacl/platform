package com.platform.cloud.common.core.entity;

import lombok.Data;

/**
 * 创建时间:2021/9/28
 * 创建人:pmc
 * 描述:
 */
@Data
public class ExMessage{
    /**
     * statusMessage 状态信息
     */
    private String message;
    /**
     * statusCode 状态码
     */
    private int code;

    public ExMessage(int code,String message){
        this.message = message;
        this.code = code;
    }

    public ExMessage(int code){
        this.message = "操作失败,请核对状态码";
        this.code = code;
    }
}
